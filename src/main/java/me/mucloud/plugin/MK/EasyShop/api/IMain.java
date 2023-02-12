package me.mucloud.plugin.MK.EasyShop.api;

import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public interface IMain{
    ConsoleCommandSender getCCS();

    Player getPlayer(String s);

}
