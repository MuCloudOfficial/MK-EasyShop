package me.mucloud.plugin.MK.EasyShop.gui;

import me.mucloud.plugin.MK.EasyShop.internal.Configuration;
import me.mucloud.plugin.MK.EasyShop.internal.Messages;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class MainView extends View {

    private final int Size = 27;
    private Inventory Inv;
    private String Title;
    private String BuyTitle;
    private String SellTitle;
    private String SkullTitle;
    private String SkullLore;

    public MainView(Configuration c){
        super(27);

        Title = c.getGuiTitleName();
    }

    @Override public void toView(Player viewer) {
        setTitle(Messages.requestPlaceholder(viewer, Title));

        ItemStack skull = new ItemStack(Material.PLAYER_HEAD);
        ItemStack buyTitle = new ItemStack(Material.CHEST);
        ItemStack sellTitle = new ItemStack(Material.ENDER_CHEST);

        ItemMeta im_skull = skull.getItemMeta();
        ItemMeta im_buyTitle = buyTitle.getItemMeta();
        ItemMeta im_sellTitle = sellTitle.getItemMeta();

        im_skull.setDisplayName(Messages.requestPlaceholder(viewer, Messages.SHOP_GUI_SKULL_TITLE));
        im_skull.setLore(Messages.requestPlaceholder(viewer, Messages.SHOP_GUI_SKULL_LORE));
        im_buyTitle.setDisplayName(Messages.requestPlaceholder(viewer, Messages.SHOP_GUI_BUY));
        im_sellTitle.setDisplayName(Messages.requestPlaceholder(viewer, Messages.SHOP_GUI_SELL));

        skull.setItemMeta(im_skull);
        buyTitle.setItemMeta(im_buyTitle);
        sellTitle.setItemMeta(im_sellTitle);

        Inv = Bukkit.createInventory(null, getViewSize(), getTitle());
        viewer.closeInventory();
        viewer.openInventory(Inv);
    }

}
