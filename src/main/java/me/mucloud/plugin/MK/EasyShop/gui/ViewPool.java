package me.mucloud.plugin.MK.EasyShop.gui;

import me.mucloud.plugin.MK.EasyShop.api.IView;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class ViewPool {

    private static List<IView> Pool = new ArrayList<>();

    public static<T extends View> void regView(T v){
        Pool.add(v);
    }

    public static void unregView(Player p){
        for(IView iv : Pool){
            if(iv.isView(p)){
                iv.closeView();
            }
        }
    }



}
