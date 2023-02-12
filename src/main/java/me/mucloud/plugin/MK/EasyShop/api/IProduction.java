package me.mucloud.plugin.MK.EasyShop.api;

import me.mucloud.plugin.MK.EasyShop.core.Production;
import org.bukkit.entity.Player;

public interface IProduction {

    void toIcon();

    void refresh();

    boolean equals(int id);

    void toPlayer(Player p);

    boolean equals(Production p);
}
