package me.mucloud.plugin.MK.EasyShop.api;

import me.mucloud.plugin.MK.EasyShop.core.Product;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public interface IProduct extends Viewable{

    ItemStack toIcon();

    void refresh();

    boolean equals(int id);

    void toPlayer(Player p);

    boolean equals(Product p);
}
