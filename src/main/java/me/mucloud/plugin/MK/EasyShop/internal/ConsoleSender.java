package me.mucloud.plugin.MK.EasyShop.internal;

import me.mucloud.plugin.MK.EasyShop.Main;
import me.mucloud.plugin.MK.EasyShop.internal.Configuration;
import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;

import java.util.logging.Logger;

public final class ConsoleSender {

    private static final ConsoleCommandSender CCS = Bukkit.getConsoleSender();
    private static final Logger LOGGER = Logger.getLogger("The Adv.Tech of TwilightCloudPavilion");

    public static void sendConsoleMessage(String s){
        CCS.sendMessage(s);
    }

    public static void info(String s){
        LOGGER.info(s);
    }

    public static void warn(String s){
        LOGGER.warning(s);
    }

    public static void err(String s){
        LOGGER.severe(s);
    }

}
