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
        plugin.disablePlayerFlight(event.player)
    }

    @EventHandler
    fun onPlayerInventoryClick(event: PlayerInventorySlotChangeEvent){
        plugin.disablePlayerFlight(event.player)
    }


    @EventHandler
    fun onPlayerMove(event: PlayerMoveEvent){
        plugin.disablePlayerFlight(event.player)
    }
}