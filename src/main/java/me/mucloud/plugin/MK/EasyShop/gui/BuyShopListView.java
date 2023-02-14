package me.mucloud.plugin.MK.EasyShop.gui;

import me.mucloud.plugin.MK.EasyShop.core.BuyProduct;
import me.mucloud.plugin.MK.EasyShop.core.Shop;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class BuyShopListView extends FlippableView<Shop<BuyProduct>>{


    public BuyShopListView(String title, List<Shop<BuyProduct>> initContent, Player viewer) {
        super(title, initContent, viewer);
    }

    @Override
    public void refreshView() {

    }

    @Override
    public void addContent(ItemStack... is) {

    }

    @Override
    public void setContent(int index, ItemStack is) {

    }

    @Override
    public List<ItemStack> getViewContent() {
        return null;
    }

    @Override
    public void setViewContent(List<ItemStack> content) {

    }
}
