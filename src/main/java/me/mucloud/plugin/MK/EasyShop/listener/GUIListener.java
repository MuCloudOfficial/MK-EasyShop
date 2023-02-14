package me.mucloud.plugin.MK.EasyShop.listener;

import me.mucloud.plugin.MK.EasyShop.gui.MainView;
import me.mucloud.plugin.MK.EasyShop.gui.View;
import me.mucloud.plugin.MK.EasyShop.gui.ViewPool;
import me.mucloud.plugin.MK.EasyShop.internal.Configuration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class GUIListener implements Listener {

    private final Configuration C;

    public GUIListener(Configuration c){
        C = c;
    }

    @EventHandler public void onListenMainView(InventoryClickEvent ice){
        Player p = (Player)ice.getWhoClicked();
        if(ViewPool.getView(p) != null){
            View v = ViewPool.getView(p);
            if(v instanceof MainView){
                if(ice.getRawSlot() == 10){
                    ((MainView) v).toViewBuyShopList(p);
                }else if(ice.getRawSlot() == 16){
                    ((MainView) v).toViewSellShopList(p);
                }else if(ice.getRawSlot() == 13){
                    p.sendMessage("CHANGEINFO");
                    ((MainView) v).changeInfo();
                    ViewPool.getViewers(v).forEach(Player::updateInventory);
                }
            }
            ice.setCancelled(true);
        }
    }

}
