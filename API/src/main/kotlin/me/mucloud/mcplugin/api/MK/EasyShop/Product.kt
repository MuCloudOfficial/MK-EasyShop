package me.mucloud.mcplugin.api.MK.EasyShop

import org.bukkit.inventory.ItemStack

interface Product {

    fun getID(): Int

    fun getPrice(): Double

    fun setPrice(price: Double)

    fun toIcon(): ItemStack

    override fun equals(other: Any?): Boolean

    override fun hashCode(): Int

}