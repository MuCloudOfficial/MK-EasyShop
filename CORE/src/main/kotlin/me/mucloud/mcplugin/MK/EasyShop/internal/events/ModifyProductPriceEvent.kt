package me.mucloud.mcplugin.MK.EasyShop.internal.events

import me.mucloud.mcplugin.MK.EasyShop.core.Product
import org.bukkit.event.Cancellable
import org.bukkit.event.Event
import org.bukkit.event.HandlerList

class ModifyProductPriceEvent(
    private val product: Product,
    private var price: Double
): Event(), Cancellable {

    private var isCancelled = false

    fun getProduct(): Product = product

    fun getPrice(): Double = price

    fun setPrice(price: Double): Double = price.also { product.price = it }

    override fun getHandlers(): HandlerList = HandlerList()

    override fun isCancelled(): Boolean  = isCancelled

    override fun setCancelled(cancel: Boolean) {
        isCancelled = cancel
    }

}