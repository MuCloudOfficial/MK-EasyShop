package me.mucloud.plugin.MK.EasyShop.api;

public interface IDataView<T extends Viewable> extends IView{

    T getData(int index);

    void refreshView();

}
