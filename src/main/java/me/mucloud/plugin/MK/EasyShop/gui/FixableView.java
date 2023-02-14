package me.mucloud.plugin.MK.EasyShop.gui;

import me.mucloud.plugin.MK.EasyShop.internal.Messages;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public abstract class FixableView extends View{

    private List<ItemStack> Content;

    public FixableView(String title, int viewSize, Player viewer) {
        super(title, viewSize, viewer);

        Content = new ArrayList<>(viewSize);
    }

    @Override public void addContent(ItemStack... is) {
        if(Content.size() + is.length <= getViewSize()){
            Content.addAll(List.of(is));
        }
    }

    @Override public void setContent(int index, ItemStack is) {
        Content.set(index, is);
    }

    @Override public List<ItemStack> getViewContent() {
        return Content;
    }

    @Override public void setViewContent(List<ItemStack> content) {
        Content = content;
    }


}
