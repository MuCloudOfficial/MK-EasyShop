package me.mucloud.plugin.MK.EasyShop.api;

import me.mucloud.plugin.MK.EasyShop.core.Production;
import me.mucloud.plugin.MK.EasyShop.core.Shop;
import me.mucloud.plugin.MK.EasyShop.core.ShopPool;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public interface IShop<T extends Production> {

    String getID();

    ShopPool.ShopType getShopType();

    boolean isEnabled();

    void close();

    void open();

    String getShopName();

    void setShopName(String name);

    void setIcon(Material icon);

    ItemStack toIcon();

    void setPermission(String permission);

    boolean checkPermission(Player p);

    void refresh();

    int getRefreshInterval();

    void setRefreshTime(int interval);

    abstract int addProduction(T production);

    abstract int removeProduction(T production);

    IProduction getProduction(int index);

    List<T> toList();

    boolean equals(String id);

    boolean equals(Shop<?> s);

}
