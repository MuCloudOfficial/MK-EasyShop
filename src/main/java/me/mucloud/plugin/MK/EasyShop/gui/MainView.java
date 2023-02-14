package me.mucloud.plugin.MK.EasyShop.gui;

import me.mucloud.plugin.MK.EasyShop.internal.Configuration;
import me.mucloud.plugin.MK.EasyShop.internal.Messages;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class MainView extends FixableView{

    private Configuration C;
    private Inventory Inv;

    public MainView(Configuration C, Player viewer) {
        super(Messages.requestPlaceholder(viewer, C.getGuiTitleName()), 27, viewer);
    }

    public void toView(){
        ItemStack content = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
        ItemStack buyIcon = new ItemStack(Material.CHEST);
        ItemStack sellIcon = new ItemStack(Material.ENDER_CHEST);
        ItemStack skull = new ItemStack(Material.PLAYER_HEAD);

        ItemMeta im_content = content.getItemMeta();
        ItemMeta im_buyIcon = buyIcon.getItemMeta();
        ItemMeta im_sellIcon = sellIcon.getItemMeta();
        ItemMeta im_skull = skull.getItemMeta();

        im_content.setDisplayName("");
        im_buyIcon.setDisplayName(Messages.requestPlaceholder(getViewer(), Messages.SHOP_GUI_BUY));
        im_sellIcon.setDisplayName(Messages.requestPlaceholder(getViewer(), Messages.SHOP_GUI_SELL));
        im_skull.setDisplayName(Messages.requestPlaceholder(getViewer(), Messages.SHOP_GUI_SKULL_TITLE));
        im_skull.setLore(Messages.requestPlaceholder(getViewer(), Messages.SHOP_GUI_SKULL_LORE));

        setViewContent(List.of(
                content, content, content, content, content, content, content, content, content,
                content, buyIcon, content, content, skull, content, content, sellIcon, content,
                content, content, content, content, content, content, content, content, content
        ));

        getViewContent().forEach(Inv::addItem);
        getViewer().closeInventory();
        getViewer().openInventory(Inv);
    }

    @Override public void refreshView() {}

}
