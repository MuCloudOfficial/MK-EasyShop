package me.mucloud.mcplugin.MK.EasyShop.internal.events

import me.mucloud.mcplugin.api.MK.EasyShop.Shop
import org.bukkit.event.Cancellable
import org.bukkit.event.Event
import org.bukkit.event.HandlerList

class DelShopEvent(
    val shop: Shop
): Event(), Cancellable{

    var isCancelled = false

    override fun getHandlers(): HandlerList = HandlerList()

    override fun isCancelled(): Boolean = isCancelled

    override fun setCancelled(cancel: Boolean){
        isCancelled = cancel
    }

}