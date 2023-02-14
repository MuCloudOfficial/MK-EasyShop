package me.mucloud.plugin.MK.EasyShop.gui;

import me.mucloud.plugin.MK.EasyShop.api.Viewable;
import me.mucloud.plugin.MK.EasyShop.internal.Messages;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public abstract class FlippableView<T extends Viewable> extends View{

    private List<T> Content;
    private int Page;

    public FlippableView(String title, List<T> initContent, Player viewer){
        super(title, 45, viewer);

        Content = initContent;

        Page = 0;
    }

    public void setPageView(int page){
        Page = page;
    }

    public void toView(){
        ItemStack previousButton = new ItemStack(Material.PAPER);
        ItemStack nextButton = new ItemStack(Material.PAPER);
        ItemStack fooBarContent = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
        List<ItemStack> fooBar = List.of(
                fooBarContent, fooBarContent, previousButton, fooBarContent, fooBarContent, fooBarContent, nextButton, fooBarContent, fooBarContent
        );
        List<ItemStack> noPreviousFooBar = List.of(
                fooBarContent, fooBarContent, fooBarContent, fooBarContent, fooBarContent, fooBarContent, nextButton, fooBarContent, fooBarContent
        );
        List<ItemStack> noNextFooBar = List.of(
                fooBarContent, fooBarContent, previousButton, fooBarContent, fooBarContent, fooBarContent, fooBarContent, fooBarContent, fooBarContent
        );
        List<ItemStack> noFlipFooBar = List.of(
                fooBarContent, fooBarContent, fooBarContent, fooBarContent, fooBarContent, fooBarContent, fooBarContent, fooBarContent, fooBarContent
        );

        List<ItemStack> viewList = new ArrayList<>();
        if(Page == 0){
            if(Content.size() <= getViewSize() - 9){
                viewList.addAll(getViewSize(), noFlipFooBar);
            }else{
                viewList.addAll(getViewSize(), noPreviousFooBar);
            }
        }else{
            if(Content.size() <= (Page + 1) * getViewSize() - 9){
                viewList.addAll(getViewSize(), noNextFooBar);
            }else{
                viewList.addAll(getViewSize(), fooBar);
            }
        }
        try{
            Content.subList(Page * 45, (Page + 1) * 45).forEach(Viewable::toIcon);
        }finally {
            Inventory inv = Bukkit.createInventory(null, getViewSize(), Messages.requestPlaceholder(getViewer(), getTitle()));
            viewList.forEach(inv::addItem);
            getViewer().closeInventory();
            getViewer().openInventory(inv);
        }

        super.toView();
    }

    public abstract void refreshView();

}
