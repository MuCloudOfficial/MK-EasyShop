package me.mucloud.plugin.MK.EasyShop.gui;

import me.mucloud.plugin.MK.EasyShop.api.IView;
import org.bukkit.entity.Player;

public abstract class View implements IView{

    private String Title;
    private final int ViewSize;
    private final Player Viewer;

    public View(Player viewer, int viewSize){
        Viewer = viewer;
        ViewSize = viewSize;
        Title = "";
    }

    public Player getViewer() {
        return Viewer;
    }
    public boolean isView(Player viewer){
        return Viewer.equals(viewer);
    }
    public void setTitle(String title){
        Title = title;
    }
    public String getTitle(){
        return Title;
    }
    public int getViewSize() {
        return ViewSize;
    }
    public void closeView(){
        ViewPool.closeView(this);
    }
    public boolean equals(View v){
        return Title.equals(v.Title);
    }
    public abstract void toView();

}
