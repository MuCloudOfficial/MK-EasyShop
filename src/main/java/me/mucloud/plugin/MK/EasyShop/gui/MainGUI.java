package me.mucloud.plugin.MK.EasyShop.gui;

import me.mucloud.plugin.MK.EasyShop.api.IConfiguration;
import me.mucloud.plugin.MK.EasyShop.internal.Messages;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

public class MainGUI{

    private Inventory GUI;
    private static String GUITitle;
    private ItemStack content;
    private ItemStack BUY;
    private ItemStack SELL;
    private ItemStack SKULL;

    public MainGUI(IConfiguration c){
        GUITitle = c.getGuiTitleName();
        content = new ItemStack(Material.GRAY_STAINED_GLASS_PANE, 1);
        BUY = new ItemStack(Material.CHEST, 1);
        SELL = new ItemStack(Material.ENDER_CHEST, 1);
        SKULL = new ItemStack(Material.PLAYER_HEAD, 1);
    }

    public void showTo(Player p){
        GUI = Bukkit.createInventory(null, 27, Messages.requestPlaceholder(p, GUITitle));
        ItemMeta im_buy = BUY.getItemMeta();
        ItemMeta im_sell = SELL.getItemMeta();
        ItemMeta im_skull = SKULL.getItemMeta();
        im_buy.setDisplayName(Messages.requestPlaceholder(p, Messages.SHOP_GUI_BUY));
        im_sell.setDisplayName(Messages.requestPlaceholder(p, Messages.SHOP_GUI_SELL));
        ((SkullMeta)im_skull).setOwningPlayer(p);
        im_skull.setDisplayName(Messages.requestPlaceholder(p, Messages.SHOP_GUI_SKULL_TITLE));
        im_skull.setLore(Messages.requestPlaceholder(p, Messages.SHOP_GUI_SKULL_LORE));

        GUI.setContents(new ItemStack[]
                {
                        content, content, content, content, content, content, content, content, content,
                        content, BUY, content, content, SKULL, content, content, SELL, content,
                        content, content, content, content, content, content, content, content, content,
                });
        p.openInventory(GUI);
    }

}
