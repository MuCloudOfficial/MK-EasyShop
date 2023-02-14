package me.mucloud.plugin.MK.EasyShop.listener;

import me.mucloud.plugin.MK.EasyShop.internal.Configuration;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class GUIListener implements Listener {

    private Configuration C;

    public GUIListener(Configuration c){
        C = c;
    }

    @EventHandler public void onListen(InventoryClickEvent ice){

    }

}
