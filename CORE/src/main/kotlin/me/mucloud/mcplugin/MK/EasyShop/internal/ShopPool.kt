package me.mucloud.mcplugin.MK.EasyShop.internal

import me.mucloud.mcplugin.api.MK.EasyShop.Shop
import org.jetbrains.annotations.Nullable

object ShopPool {

    val POOL: MutableList<Shop> = mutableListOf()

    fun addShop(shop: Shop): Boolean {
        if(getShop(shop) == null) {
            return false
        }
        POOL.add(shop)
        return true
    }

    fun delShop(id: Int): Boolean{
        getShop(id).also{
            if(it == null){
                return false
            }
            POOL.remove(it)
            return true
        }
    }

    @Nullable fun getShop(id: Int): Shop? {
        POOL.forEach{
            if(it.getID() == id){
                return it
            }
        }
        return null
    }

    @Nullable fun getShop(shop: Shop): Shop?{
        POOL.forEach{
            if(it == shop){
                return it
            }
        }
        return null
    }

}

object ShopPoolView{



}