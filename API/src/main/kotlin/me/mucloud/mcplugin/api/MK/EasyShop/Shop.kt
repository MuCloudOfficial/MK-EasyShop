package me.mucloud.mcplugin.api.MK.EasyShop

import org.bukkit.entity.Player
import org.bukkit.inventory.Inventory

interface Shop {

    fun getShopName(): String

    fun getShopDescription(): String

    fun getOriginViewport(): Inventory?

    fun addProduct(product: Product): Boolean

    fun removeProduct(): Boolean

    fun getProduct(id: Int): Product?

    fun deal(player: Player, product: Product): Boolean

    fun show(player: Player){
        getOriginViewport()?.viewers?.add(player)
    }

    fun closeShow(){
        getOriginViewport()?.viewers?.clear()
    }

}