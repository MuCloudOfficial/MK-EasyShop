package me.mucloud.plugin.MK.EasyShop.core;

import me.mucloud.plugin.MK.EasyShop.Main;
import me.mucloud.plugin.MK.EasyShop.api.IProduction;
import me.mucloud.plugin.MK.EasyShop.api.IShop;
import me.mucloud.plugin.MK.EasyShop.internal.Messages;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.jetbrains.annotations.Nullable;

import java.text.SimpleDateFormat;
import java.util.*;

public class Shop<T extends Production> implements IShop<T> {

    private boolean Enabled;
    private final String ID;
    private static String ShopName;
    private static ShopPool.ShopType ShopType;
    private Material ICON;
    private String AccessPermission;

    private boolean AutoRefreshEnabled;
    private int RefreshInterval;
    private long Remaining;
    private BukkitTask RefreshTask;

    private Inventory ShopInv;
    private final List<T> ProductionList;

    Shop(String id, ShopPool.ShopType shopType, String shopName, Material icon, @Nullable String accessPermission, int refreshInterval){
        ID = id;
        ShopType = shopType;
        ShopName = shopName;
        ICON = icon;
        AccessPermission = accessPermission;
        ProductionList = new ArrayList<>();
        if(refreshInterval != -1){
            AutoRefreshEnabled = true;
            setRefreshTime(refreshInterval);
            autoRefreshTask();
        }

        ShopInv = Bukkit.createInventory(null, 54);
        Enabled = true;
    }

    private void autoRefreshTask(){
        RefreshTask = new BukkitRunnable() {
            @Override public void run() {
                if(Remaining < 0){
                    refresh();
                }else{
                    Remaining -= 1000;
                }
                ShopName = ShopName + getRemaining();
            }
        }.runTaskTimerAsynchronously(Main.plugin, 0, 20L);
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
        if(Enabled){
            if(AutoRefreshEnabled){
                RefreshTask.cancel();
            }
            Enabled = false;
        }
    }

    @Override public void open() {
        if(!Enabled){
            if(AutoRefreshEnabled){
                autoRefreshTask();
            }
            Enabled = true;
        }
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
        if(Messages.Locale.equals("en_US")){
            return new SimpleDateFormat("ddD HHH mmM ssS").format(Remaining);
        }else{
            return new SimpleDateFormat("dd天 HH时 mm分 ss秒").format(Remaining);
        }
    }

    @Override public int getRefreshInterval(){
        return RefreshInterval;
    }

    @Override public void setRefreshTime(int interval){
        if(RefreshTask != null){
            if(!RefreshTask.isCancelled()){
               RefreshTask.cancel();
            }
        }
        RefreshInterval = interval;
        Calendar c = Calendar.getInstance();
        c.set(c.get(Calendar.YEAR),
                c.get(Calendar.MONTH),
                c.get(Calendar.DAY_OF_MONTH),
                c.get(Calendar.HOUR_OF_DAY),
                c.get(Calendar.MINUTE),
                c.get(Calendar.SECOND));
        long refreshDate = c.getTimeInMillis();
        Remaining = refreshDate - new Date().getTime();
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
