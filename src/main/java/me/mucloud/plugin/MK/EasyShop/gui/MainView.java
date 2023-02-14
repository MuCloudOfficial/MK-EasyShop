package me.mucloud.plugin.MK.EasyShop.gui;

import me.mucloud.plugin.MK.EasyShop.internal.Configuration;
import me.mucloud.plugin.MK.EasyShop.internal.Messages;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Collections;

public class MainView extends View {

    private final int Size = 27;
    private Inventory Inv;

    public MainView(Configuration c, Player p){
        super(p, 27);

        setTitle(Messages.requestPlaceholder(p, c.getGuiTitleName()));
    }

    @Override public void toView() {
        ItemStack content = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
        ItemStack skull = new ItemStack(Material.PLAYER_HEAD);
        ItemStack buyTitle = new ItemStack(Material.CHEST);
        ItemStack sellTitle = new ItemStack(Material.ENDER_CHEST);

        ItemMeta im_content = content.getItemMeta();
        ItemMeta im_skull = skull.getItemMeta();
        ItemMeta im_buyTitle = buyTitle.getItemMeta();
        ItemMeta im_sellTitle = sellTitle.getItemMeta();

        im_content.setDisplayName("");
        im_skull.setDisplayName(Messages.requestPlaceholder(getViewer(), Messages.SHOP_GUI_SKULL_TITLE));
        im_skull.setLore(Messages.requestPlaceholder(getViewer(), Messages.SHOP_GUI_SKULL_LORE));
        im_buyTitle.setDisplayName(Messages.requestPlaceholder(getViewer(), Messages.SHOP_GUI_BUY));
        im_sellTitle.setDisplayName(Messages.requestPlaceholder(getViewer(), Messages.SHOP_GUI_SELL));

        content.setItemMeta(im_content);
        skull.setItemMeta(im_skull);
        buyTitle.setItemMeta(im_buyTitle);
        sellTitle.setItemMeta(im_sellTitle);

        Inv = Bukkit.createInventory(null, getViewSize(), getTitle());
        Inv.setContents(new ItemStack[]{
                content, content, content, content, content, content, content, content, content,
                content, buyTitle, content, content, skull, content, content, sellTitle, content,
                content, content, content, content, content, content, content, content, content,
        });
        getViewer().closeInventory();
        getViewer().openInventory(Inv);
    }

    public void changeInfo(){
        ItemStack skull = new ItemStack(Material.PLAYER_HEAD);
        ItemMeta im_skull = skull.getItemMeta();
        im_skull.setDisplayName(Messages.requestPlaceholder(getViewer(), "Messages.SHOP_GUI_SKULL_TITLE"));
        im_skull.setLore(Collections.singletonList(Messages.requestPlaceholder(getViewer(), "Messages.SHOP_GUI_SKULL_LORE")));
        skull.setItemMeta(im_skull);

        Inv.setItem(13, skull);
        getViewer().updateInventory();
    }

    public void toViewBuyShopList(Player p){

    }

    public void toViewSellShopList(Player p){

    }

}
