package me.mucloud.plugin.MK.EasyShop.gui;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ViewPool {

    private static final Map<Player, View> Pool = new HashMap<>();

    public static void toView(Player viewer, View view){
        Pool.put(viewer, view);
        view.toView(viewer);
    }

    public static void closeView(View v){
        for(Map.Entry<Player, View> e : Pool.entrySet()){
            if(e.getValue().equals(v)){
                e.getValue().closeView();
                Pool.remove(e.getKey(), e.getValue());
            }
        }
    }

    public static void closeView(Player viewer, View view){
        for(Map.Entry<Player, View> e : Pool.entrySet()){
            if(e.getKey().equals(viewer) && e.getValue().equals(view)){
                e.getValue().closeView();
                Pool.remove(e.getKey(), e.getValue());
            }
        }
    }

    public static List<Player> getViewers(View v){
        List<Player> viewers = new ArrayList<>();
        Pool.forEach( (p, vl) -> {
            if(vl.equals(v)){
                viewers.add(p);
            }
        });
        return viewers;
    }

    public static View getView(Player viewer){
        if(Pool.containsKey(viewer)){
            return Pool.get(viewer);
        }
        return null;
    }

}
