package org.alvindimas05.mdantifly

import io.papermc.paper.event.player.PlayerInventorySlotChangeEvent
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.event.player.PlayerMoveEvent

class PlayerEvent (
    private val plugin: MythicDungeonsAntiFly
): Listener {
    @EventHandler
    fun onPlayerInteract(event: PlayerInteractEvent){
        if(plugin.dungeonWorlds.any { event.player.world.name.startsWith(it) }) {
            plugin.disablePlayerFlight(event.player)
        }
    }

    @EventHandler
    fun onPlayerInventoryClick(event: PlayerInventorySlotChangeEvent){
        if(plugin.dungeonWorlds.any { event.player.world.name.startsWith(it) }) {
            plugin.disablePlayerFlight(event.player)
        }
    }


    @EventHandler
    fun onPlayerMove(event: PlayerMoveEvent){
        if(plugin.dungeonWorlds.any { event.player.world.name.startsWith(it) }) {
            plugin.disablePlayerFlight(event.player)
        }
    }
}