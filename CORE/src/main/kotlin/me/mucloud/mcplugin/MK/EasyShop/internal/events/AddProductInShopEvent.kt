package me.mucloud.mcplugin.MK.EasyShop.internal.events

import me.mucloud.mcplugin.api.MK.EasyShop.Product
import me.mucloud.mcplugin.api.MK.EasyShop.Shop
import org.bukkit.event.Cancellable
import org.bukkit.event.Event
import org.bukkit.event.HandlerList

class AddProductInShopEvent(
    private val shop: Shop,
    private val product: Product
): Event(), Cancellable{

    private var isCancelled = false

    fun getProduct(): Product = product

    fun getShop(): Shop = shop

    override fun isCancelled(): Boolean = isCancelled

    override fun setCancelled(cancel: Boolean) {
        isCancelled = cancel
    }

    override fun getHandlers(): HandlerList = HandlerList()

}