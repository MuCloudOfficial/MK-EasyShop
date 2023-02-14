package me.mucloud.plugin.MK.EasyShop.core;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class BuyProduct extends Product {

    public BuyProduct(int id, Material type, int price, int amount) {
        super(id, type, price, amount);
    }

    public BuyProduct(int id, ItemStack is, int price, int amount){
        super(id, is, price, amount);
    }

    @Override public ItemStack toIcon() {

        return null;
    }

    @Override public void toPlayer(Player p) {

    }


}
