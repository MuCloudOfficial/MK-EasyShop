package me.mucloud.mcplugin.MK.EasyShop.internal.events

import me.mucloud.mcplugin.api.MK.EasyShop.Product
import org.bukkit.event.Cancellable
import org.bukkit.event.Event
import org.bukkit.event.HandlerList

class AddProductInPoolEvent(
    private val product: Product,
): Event(), Cancellable {

    private var isCancelled = false

    fun getProduct(): Product = product

    override fun getHandlers(): HandlerList = HandlerList()

    override fun isCancelled(): Boolean  = isCancelled

    override fun setCancelled(cancel: Boolean) {
        isCancelled = cancel
    }

}