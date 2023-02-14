package me.mucloud.plugin.MK.EasyShop.listener;

import me.mucloud.plugin.MK.EasyShop.gui.View;
import me.mucloud.plugin.MK.EasyShop.gui.ViewPool;
import me.mucloud.plugin.MK.EasyShop.internal.Configuration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class GUIListener implements Listener {

    private Configuration C;

    public GUIListener(Configuration c){
        C = c;
    }

    @EventHandler public void onListen(InventoryClickEvent ice){


    }

}
