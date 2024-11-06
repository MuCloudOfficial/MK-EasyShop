package me.mucloud.mcplugin.MK.EasyShop.internal.events

import me.mucloud.mcplugin.api.MK.EasyShop.Product
import org.bukkit.event.Cancellable
import org.bukkit.event.Event
import org.bukkit.event.HandlerList

class DelProductFromPoolEvent(
    private val product: Product,
): Event(), Cancellable {

    fun getProduct(): Product = product

    private var cancel = false

    override fun getHandlers(): HandlerList = HandlerList()

    override fun isCancelled(): Boolean = cancel

    override fun setCancelled(cancel: Boolean){
        this.cancel = cancel
    }

}