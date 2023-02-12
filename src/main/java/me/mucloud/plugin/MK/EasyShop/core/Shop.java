package me.mucloud.plugin.MK.EasyShop.core;

import me.mucloud.plugin.MK.EasyShop.Main;
import me.mucloud.plugin.MK.EasyShop.api.IProduction;
import me.mucloud.plugin.MK.EasyShop.api.IShop;
import me.mucloud.plugin.MK.EasyShop.gui.ShopGUI;
import me.mucloud.plugin.MK.EasyShop.internal.Messages;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;


public class Shop<T extends Production> implements IShop<T> {

    private boolean Enabled;
    private final String ID;
    private String ShopName;
    private ShopPool.ShopType ShopType;
    private Material ICON;
    private String AccessPermission;
    private ShopRefreshTask RefreshTask;

    private ShopGUI GUI;
    private final List<T> ProductionList;

    Shop(String id, ShopPool.ShopType shopType, String shopName, Material icon, @Nullable String accessPermission, int refreshInterval){
        ID = id;
        ShopType = shopType;
        ShopName = shopName;
        ICON = icon;
        AccessPermission = accessPermission;
        ProductionList = new ArrayList<>();
        if(refreshInterval != -1){
            RefreshTask = new ShopRefreshTask(this);
        }

        GUI = null; //TODO
        Enabled = true;
    }


    @Override public String getID(){
        return ID;
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
        ProductionList.forEach(Production::refresh);
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
        for(Production p : ProductionList){
            if(p.equals(production)){
                return 1;
            }
        }
        ProductionList.add(production);
        return 0;
    }

    @Override public int removeProduction(T production){
        int index = -1;
        for(Production p : ProductionList){
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

    @Override public IProduction getProduction(int index){
        return ProductionList.get(index);
    }

    @Override public boolean equals(String id){
        return ID.equals(id);
    }

    @Override public boolean equals(Shop<?> s) {
        return (Objects.equals(ID, s.ID) && ICON == s.ICON) || ShopName.equals(s.getShopName());
    }

}
