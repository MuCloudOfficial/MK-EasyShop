package me.mucloud.plugin.MK.EasyShop.gui;

import me.mucloud.plugin.MK.EasyShop.api.IView;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public abstract class View implements IView {

    private String Title;
    private final int ViewSize;
    private final Player Viewer;

    public View(String title, int viewSize, Player viewer){
        Title = title;
        ViewSize = viewSize;
        Viewer = viewer;
    }

    @Override public void setTitle(String title){

    }

    @Override public String getTitle(){
        return Title;
    }

    @Override public int getViewSize(){
        return ViewSize;
    }

    @Override public Player getViewer(){
        return Viewer;
    }

    @Override public boolean isView(Player p) {
        return Viewer.equals(p);
    }

    @Override public void toView(){
        ViewPool.regView(this);
    }

    @Override public void closeView(){
        ViewPool.unregView(Viewer);
    }

    public abstract void addContent(ItemStack... is);
    public abstract void setContent(int index, ItemStack is);
    public abstract List<ItemStack> getViewContent();
    public abstract void setViewContent(List<ItemStack> content);
    public abstract void refreshView();

}
