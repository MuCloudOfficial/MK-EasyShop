package me.mucloud.plugin.MK.EasyShop.api;

import me.mucloud.plugin.MK.EasyShop.core.Product;
import me.mucloud.plugin.MK.EasyShop.core.Shop;
import me.mucloud.plugin.MK.EasyShop.core.ShopPool;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public interface IShop<T extends Product> extends Viewable{

    ShopPool.ShopType getShopType();

    boolean isEnabled();

    void close();

    void open();

    String getShopName();

    void setShopName(String name);

    void setIcon(Material icon);

    ItemStack toIcon();

    Inventory toInv(Player p); //todo

    void setPermission(String permission);

    boolean checkPermission(Player p);

    void refresh();

    int getRefreshInterval();

    void setRefreshTime(int interval);

    abstract int addProduction(T production);

    abstract int removeProduction(T production);

    IProduct getProduction(int index);

    List<T> toList();

    boolean equals(String id);

    boolean equals(Shop<?> s);

}
