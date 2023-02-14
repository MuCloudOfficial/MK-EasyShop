package me.mucloud.plugin.MK.EasyShop.core;

import me.mucloud.plugin.MK.EasyShop.api.IProduct;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public abstract class Product implements IProduct {

    private final int ID;
    private final ItemStack Type;
    private final int Price;
    private final int MaxAmount;
    private int Amount;

    public Product(int id, Material type, int price, int maxAmount) {
        ID = id;
        Type = new ItemStack(type,1);
        Price = price;
        MaxAmount = maxAmount;
        Amount = MaxAmount;
    }

    public Product(int id, @NotNull ItemStack is, int price, int maxAmount){
        ID = id;
        Type = is;
        Type.setAmount(1);
        Price = price;
        MaxAmount = maxAmount;
        Amount = MaxAmount;
    }

    @Override public abstract ItemStack toIcon();

    @Override public void refresh() {
        Amount = MaxAmount;
    }

    @Override public boolean equals(int id) {
        return ID == id;
    }

    @Override public boolean equals(Product p){
        return equals(p.ID);
    }


}
