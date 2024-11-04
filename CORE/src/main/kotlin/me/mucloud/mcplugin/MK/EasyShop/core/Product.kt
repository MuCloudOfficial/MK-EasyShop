package me.mucloud.mcplugin.MK.EasyShop.core

import me.mucloud.mcplugin.MK.EasyShop.internal.events.ModifyProductPriceEvent
import me.mucloud.mcplugin.api.MK.EasyShop.Product
import org.bukkit.Bukkit
import org.bukkit.inventory.ItemStack

class Product(
    internal val id: Int,
    internal val item: ItemStack,
    internal val amount: Int,
    internal val desc: List<String>,
    internal var price: Double = -1.0
): Product {

    override fun getID(): Int = id

    override fun getPrice(): Double = price

    override fun setPrice(price: Double){
        this.price = if(price <= 0){
            -1.0
        }else{
            price
        }
        Bukkit.getPluginManager().callEvent(ModifyProductPriceEvent(this, this.price))
    }

    override fun toIcon(): ItemStack = ItemStack(item).also { it.amount = 1 }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as me.mucloud.mcplugin.MK.EasyShop.core.Product

        if (id != other.id) return false
        if (item != other.item) return false
        if (amount != other.amount) return false
        if (desc != other.desc) return false
        if (price != other.price) return false

        return true
    }

    override fun hashCode(): Int {
        return 7* id + item.hashCode() + 7* amount + 31* price.hashCode()
    }

}