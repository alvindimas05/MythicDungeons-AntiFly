package org.alvindimas05.mdantifly

import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.command.TabCompleter
import org.bukkit.entity.Player
import org.bukkit.event.HandlerList
import org.bukkit.plugin.java.JavaPlugin
import kotlin.collections.ArrayList


class MythicDungeonsAntiFly : JavaPlugin() {
    var dungeonWorlds = mutableListOf<String>()
    override fun onEnable() {
        saveDefaultConfig()

        getCommand("mythicdungeonsantifly")?.setExecutor(CommandListener(this))
        getCommand("mythicdungeonsantifly")?.setTabCompleter(CommandTabListener())

        getCommand("mdantifly")?.setExecutor(CommandListener(this))
        getCommand("mdantifly")?.setTabCompleter(CommandTabListener())

        reloadWorlds()
        reloadEvents()

        logger.info("MythicDungeons-AntiFly enabled");
    }

    override fun onDisable() {
        HandlerList.unregisterAll(this)
        logger.info("MythicDungeons-AntiFly disabled");
    }

    private fun reloadWorlds(){
        dungeonWorlds = config.getStringList("dungeon-worlds")
    }

    fun reloadEvents(){
        reloadConfig()
        if(!config.getBoolean("enabled")){
            return
        }

        Bukkit.getServer().pluginManager.registerEvents(PlayerEvent(this), this)
        logger.info("MythicDungeons-AntiFly reloaded");
    }

    fun disablePlayerFlight(player: Player) {
        if(player.allowFlight || player.isFlying){
            player.allowFlight = false
            player.isFlying = false
        }
    }
}

class CommandListener (
    private val plugin: MythicDungeonsAntiFly
): CommandExecutor {
    override fun onCommand(sender: CommandSender, cmd: Command, label: String, args: Array<out String>): Boolean {
        if((!label.equals("mythicdungeonsantifly", true) &&
            !label.equals("mdantifly", true)) ||
            args.isEmpty() || !sender.isOp) return false

        if(args[0] == "reload"){
            plugin.reloadEvents()
            return true
        }
        return false
    }
}

class CommandTabListener : TabCompleter {
    companion object {
        private val cmds: List<String> = listOf("reload")
    }
    override fun onTabComplete(sender: CommandSender, cmd: Command, alias: String, args: Array<String>): List<String>? {
        val response: MutableList<String> = ArrayList()
        if(cmd.name.equals("mythicdungeonsantifly", true) ||
                    cmd.name.equals("mdantifly", true)){
            response.addAll(cmds)
        }
        return response
    }
}