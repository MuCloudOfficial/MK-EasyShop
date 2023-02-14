package me.mucloud.plugin.MK.EasyShop.gui;

import me.mucloud.plugin.MK.EasyShop.api.IView;
import org.bukkit.entity.Player;

public abstract class View implements IView{

    private String Title;
    private final int ViewSize;

    public View(int viewSize){
        ViewSize = viewSize;
        Title = "";
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
    public abstract void toView(Player viewer);

}
