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

    fun getProduct(): Product = product

    fun getShop(): Shop = shop

    private var cancel = false

    override fun getHandlers(): HandlerList = HandlerList()

    override fun isCancelled(): Boolean = cancel

    override fun setCancelled(cancel: Boolean){
        this.cancel = cancel
    }
}