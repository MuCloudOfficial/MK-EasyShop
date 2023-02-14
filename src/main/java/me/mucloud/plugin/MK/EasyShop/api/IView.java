package me.mucloud.plugin.MK.EasyShop.api;

import me.mucloud.plugin.MK.EasyShop.gui.View;

public interface IView {

    void setTitle(String title);

    String getTitle();

    int getViewSize();

    void closeView();

    boolean equals(View v);

    void toView();

}
