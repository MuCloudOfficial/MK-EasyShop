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

    private static final Main plugin = Main.getInstance();
    private final File ShopFolder;
    private final List<Shop<? extends Product>> Pool;

    public ShopPool(){
        Pool = new ArrayList<>();
        ShopFolder = new File(plugin.getDataFolder(), "Shops");
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

    public int addBuyShop(String shopName, Material icon, @Nullable String accessPermission, int refreshInterval){
        Shop<BuyProduct> shop = new Shop<>(ShopType.BUY, shopName, icon, accessPermission, refreshInterval);
        for(Shop<?> s : Pool){
            if(s.equals(shop)){
                return 1;
            }
        }
        Pool.add(shop);
        return 0;
    }

    public int addSellShop(String shopName, Material icon, @Nullable String accessPermission, int refreshInterval){
        Shop<SellProduct> shop = new Shop<>(ShopType.SELL, shopName, icon, accessPermission, refreshInterval);
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

    public boolean contains(String shopName){
        for(Shop<?> shop : Pool){
            if(shop.equals(shopName)){
                return true;
            }
        }
        return false;
    }

    @Nullable public IShop<?> getShop(String shopName){
        for(Shop<?> shop : Pool){
            if(shop.equals(shopName)){
                return shop;
            }
        }
        return null;
    }

    @SuppressWarnings("unchecked") public List<Shop<BuyProduct>> getBuyShopList(){
        List<Shop<BuyProduct>> list = new ArrayList<>();
        for(Shop<?> shop : Pool){
            if(shop.getShopType() == ShopType.BUY){
                list.add((Shop<BuyProduct>) shop);
            }
        }
        return list;
    }

    @SuppressWarnings("unchecked") public List<Shop<SellProduct>> getSellShopList(){
        List<Shop<SellProduct>> list = new ArrayList<>();
        for(Shop<?> shop : Pool){
            if(shop.getShopType() == ShopType.SELL){
                list.add((Shop<SellProduct>) shop);
            }
        }
        return list;
    }

    public enum ShopType{
        BUY,
        SELL,
    }

}
