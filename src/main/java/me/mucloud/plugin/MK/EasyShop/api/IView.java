package me.mucloud.plugin.MK.EasyShop.api;

import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public interface IView {

    void setTitle(String title);

    String getTitle();

    int getViewSize();

    Player getViewer();

    void refreshView();

    boolean isView(Player p);

    void toView();

    void closeView();

    void setContent(int index, ItemStack is);

    void addContent(ItemStack... is);

    List<ItemStack> getViewContent();

    void setViewContent(List<ItemStack> content);

}
