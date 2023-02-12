package me.mucloud.plugin.MK.EasyShop.core;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class SellProduction extends Production{

    public SellProduction(int id, Material type, int price, int amount) {
        super(id, type, price, amount);
    }

    public SellProduction(int id, ItemStack is, int price, int amount){
        super(id, is, price, amount);
    }

    @Override public void toIcon() {

    }

    @Override public void toPlayer(Player p) {

    }

}
