package me.mucloud.mcplugin.MK.EasyShop.core

import me.mucloud.mcplugin.api.MK.EasyShop.Product
import me.mucloud.mcplugin.api.MK.EasyShop.Shop
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.Inventory

class Shop(
    internal val id: Int,
    internal val icon: Material,
    internal var name: String,
    internal var description: String,
    internal var type: ShopType,
    internal var products: MutableList<Product> = mutableListOf()
): Shop {

    private var inv: Inventory? = null

    init {
        inv = Bukkit.createInventory(null, 5, name).also{ i ->
            products.forEach{ p ->
                i.addItem(p.toIcon())
            }
        }
    }

    override fun getID(): Int = id

    override fun addProduct(product: Product): Boolean {
        if(products.contains(product)){
            return true
        }else{
            products.add(product)
            return true
        }
    }

    override fun getShopName(): String = name

    override fun getShopDescription(): String = description

    override fun getOriginViewport(): Inventory? = inv

    override fun removeProduct(): Boolean {
        return true
    }

    override fun getProduct(id: Int): Product? {
        products.forEach{
            if(it.getID() == id){
                return it
            }
        }
        return null
    }

    override fun deal(player: Player, product: Product): Boolean {
        return true
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as me.mucloud.mcplugin.MK.EasyShop.core.Shop

        if (id != other.id) return false
        if (icon != other.icon) return false
        if (name != other.name) return false
        if (description != other.description) return false
        if (type != other.type) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + icon.hashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + description.hashCode()
        result = 31 * result + type.hashCode()
        return result
    }


}