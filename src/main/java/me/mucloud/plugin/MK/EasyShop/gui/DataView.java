package me.mucloud.plugin.MK.EasyShop.gui;

import me.mucloud.plugin.MK.EasyShop.api.IDataView;
import me.mucloud.plugin.MK.EasyShop.api.Viewable;
import org.bukkit.entity.Player;

import java.util.List;

public abstract class DataView<T extends Viewable> extends View implements IDataView<T> {

    private String Title;
    private List<T> DataSource;

    public DataView(String title, int viewSize, List<T> initDataSource) {
        super(viewSize);
        Title = title;
        DataSource = initDataSource;
    }

    @Override public T getData(int index) {
        return DataSource.get(index);
    }

    @Override public void toView(Player viewer) {

    }

    public abstract void refreshView();

}
