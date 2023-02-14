package me.mucloud.plugin.MK.EasyShop.core;

import me.mucloud.plugin.MK.EasyShop.api.IShop;
import me.mucloud.plugin.MK.EasyShop.api.Viewable;
import me.mucloud.plugin.MK.EasyShop.internal.Messages;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Shop<T extends Product> implements IShop<T>, Viewable{

    private boolean Enabled;
    private String ShopName;
    private ShopPool.ShopType ShopType;
    private Material ICON;
    private List<String> ShopDetail;
    private String AccessPermission;
    private ShopRefreshTask RefreshTask;
    private final List<T> ProductionList;

    Shop(ShopPool.ShopType shopType, String shopName, Material icon, List<String> shopDetail, @Nullable String accessPermission, int refreshInterval){
        ShopType = shopType;
        ShopName = shopName;
        ICON = icon;
        ShopDetail = shopDetail;
        AccessPermission = accessPermission;
        ProductionList = new ArrayList<>();
        if(refreshInterval != -1){
            RefreshTask = new ShopRefreshTask(this);
        }

        Enabled = true;
    }

    @Override public ShopPool.ShopType getShopType(){
        return ShopType;
    }

    @Override public boolean isEnabled(){
        return Enabled;
    }

    @Override public void close() {
        Enabled = false;
    }

    @Override public void open() {
        Enabled = true;

    }

    @Override public void setShopName(String shopName){
        ShopName = shopName;
    }

    @Override public String getShopName(){
        return ShopName;
    }

    @Override public Inventory toInv(Player p) {
        //todo
        return null;
    }

    @Override public ItemStack toIcon(){
        ItemStack is = new ItemStack(ICON, 1);
        ItemMeta im = is.getItemMeta();
        im.setDisplayName(ShopName);
        im.setLore(Arrays.asList(
                Messages.SHOP_REMAINING_PREFIX,
                getRemaining()
        ));
        is.setItemMeta(im);
        return is;
    }

    @Override public void setPermission(String permission){
        AccessPermission = permission;
    }

    @Override public boolean checkPermission(Player p){
        return AccessPermission == null || p.hasPermission(AccessPermission);
    }

    @Override public void setIcon(Material icon) {
        ICON = icon;
    }

    @Override public void refresh() {
        ProductionList.forEach(Product::refresh);
    }

    public String getRemaining(){
        //todo
        return null;
    }

    @Override public int getRefreshInterval(){
        //todo
        return 0;
    }

    @Override public void setRefreshTime(int interval){
        if(RefreshTask != null){
            RefreshTask.refreshInterval(interval);
        }
    }

    @Override public int addProduction(T production){
        for(Product p : ProductionList){
            if(p.equals(production)){
                return 1;
            }
        }
        ProductionList.add(production);
        return 0;
    }

    @Override public int removeProduction(T production){
        int index = -1;
        for(Product p : ProductionList){
            if(p.equals(production)){
                index = ProductionList.indexOf(p);
            }
        }
        if(index != -1){
            ProductionList.remove(index);
            return 0;
        }
        return 1;
    }

    @Override public List<T> toList(){
        return ProductionList;
    }

    @Override public Product getProduction(int index){
        return ProductionList.get(index);
    }

    @Override public boolean equals(String shopName){
        return ShopName.equals(shopName);
    }

    @Override public boolean equals(Shop<?> s) {
        return ShopName.equals(s.getShopName()) && ICON == s.ICON;
    }

}
