package me.mucloud.mcplugin.MK.EasyShop.internal

import me.mucloud.mcplugin.MK.EasyShop.internal.events.AddProductInPoolEvent
import me.mucloud.mcplugin.api.MK.EasyShop.Product
import org.bukkit.Bukkit
import org.jetbrains.annotations.Nullable

object ProductPool {

    val POOL: MutableList<Product> = mutableListOf()

    fun addProduct(product: Product): Boolean{
        if(POOL.contains(product)){
            return false
        }else{
            Bukkit.getPluginManager().callEvent(AddProductInPoolEvent(product))  //Call Event
            POOL.add(product)
            return true
        }
    }

    fun delProduct(product: Product): Boolean{
        if(POOL.contains(product)){
            POOL.remove(product)
            return true
        }else{
            return false
        }
    }

    @Nullable fun getProduct(id: Int): Product?{
        POOL.forEach { p ->
            if(p.getID() == id){
                return p
            }
        }
        return null
    }

}