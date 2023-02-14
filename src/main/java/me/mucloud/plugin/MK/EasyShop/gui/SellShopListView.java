package me.mucloud.plugin.MK.EasyShop.gui;

import me.mucloud.plugin.MK.EasyShop.core.SellProduct;
import me.mucloud.plugin.MK.EasyShop.core.Shop;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class SellShopListView extends FlippableView<Shop<SellProduct>>{

    private List<Shop<SellProduct>> list;

    public SellShopListView(String title, List<Shop<SellProduct>> initContent, Player viewer) {
        super(title, initContent, viewer);
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

    @Override
    public void refreshView() {

    }
}
