package me.mucloud.plugin.MK.EasyShop.core;

import me.mucloud.plugin.MK.EasyShop.Main;
import me.mucloud.plugin.MK.EasyShop.api.IShop;
import org.bukkit.Material;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class ShopPool {

    private final Main Main;
    private final File ShopFolder;
    private final List<Shop<? extends Production>> Pool;

    public ShopPool(Main main){
        Main = main;
        Pool = new ArrayList<>();
        ShopFolder = new File(main.getDataFolder(), "Shops");
        if(!ShopFolder.exists() || ShopFolder.isFile()){
            ShopFolder.mkdir();
        }
    }

    public void initFromFile(){
        FileConfiguration fc = new YamlConfiguration();
        for(File f : Objects.requireNonNull(ShopFolder.listFiles())){
            try {
                fc.load(f);
                Map<String, Object> map = fc.getValues(false);

            } catch (IOException | InvalidConfigurationException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public int addBuyShop(String id, String shopName, Material icon, @Nullable String accessPermission, int refreshInterval){
        Shop<BuyProduction> shop = new Shop<>(Main, id, ShopType.BUY, shopName, icon, accessPermission, refreshInterval);
        for(Shop<?> s : Pool){
            if(s.equals(shop)){
                return 1;
            }
        }
        Pool.add(shop);
        return 0;
    }

    public int addSellShop(String id, String shopName, Material icon, @Nullable String accessPermission, int refreshInterval){
        Shop<SellProduction> shop = new Shop<>(Main, id, ShopType.SELL, shopName, icon, accessPermission, refreshInterval);
        for(Shop<?> s : Pool){
            if(s.equals(shop)){
                return 1;
            }
        }
        Pool.add(shop);
        return 0;
    }

    public IShop<?> getShop(int index){
        return Pool.get(index);
    }

    @Nullable public IShop<?> getShop(String id){
        for(Shop<?> shop : Pool){
            if(shop.equals(id)){
                return shop;
            }
        }
        return null;
    }

    @SuppressWarnings("unchecked") public List<Shop<BuyProduction>> getBuyShopList(){
        List<Shop<BuyProduction>> list = new ArrayList<>();
        for(Shop<? extends Production> shop : Pool){
            if(shop.getShopType() == ShopType.BUY){
                list.add((Shop<BuyProduction>) shop);
            }
        }
        return list;
    }

    @SuppressWarnings("unchecked") public List<Shop<SellProduction>> getSellShopList(){
        List<Shop<SellProduction>> list = new ArrayList<>();
        for(Shop<? extends Production> shop : Pool){
            if(shop.getShopType() == ShopType.SELL){
                list.add((Shop<SellProduction>) shop);
            }
        }
        return list;
    }

    public enum ShopType{
        BUY,
        SELL,
    }

}
