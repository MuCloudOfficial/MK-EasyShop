package me.mucloud.plugin.MK.EasyShop;

import me.mucloud.plugin.MK.EasyShop.api.IConfiguration;
import me.mucloud.plugin.MK.EasyShop.command.CommandManager;
import me.mucloud.plugin.MK.EasyShop.core.ShopPool;
import me.mucloud.plugin.MK.EasyShop.internal.Configuration;
import me.mucloud.plugin.MK.EasyShop.internal.ConsoleSender;
import me.mucloud.plugin.MK.EasyShop.internal.Messages;
import me.mucloud.plugin.MK.EasyShop.listener.GUIListener;
import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.util.Objects;

public class Main extends JavaPlugin{

    private static Main plugin;

    private Configuration C;
    private CommandManager CM;
    private ShopPool SP;

    private static boolean Hook_PAPI;

    @Override public void onEnable() {
        plugin = this;
        ConsoleSender.info("MADE IN STARRY SKY.");

        loadMessages();
        requestHookPlugins();
        loadConfig();
        ConsoleSender.sendConsoleMessage("§b§lMADE IN STARRY SKY.");
        ConsoleSender.sendConsoleMessage(Messages.PLUGIN_ENABLING);
        deployShops();
        regCommands();
        regListeners();

        ConsoleSender.sendConsoleMessage(Messages.PLUGIN_ENABLED);
    }

    @Override public void onDisable() {
        ConsoleSender.sendConsoleMessage(Messages.PLUGIN_DISABLING);

        unregCommands();
        unregListeners();

        ConsoleSender.sendConsoleMessage(Messages.PLUGIN_DISABLED);
    }

    public void onReload(){
        //TODO
    }

    public void requestHookPlugins(){
        if(Bukkit.getPluginManager().getPlugin("Vault") == null){
            ConsoleSender.sendConsoleMessage(Messages.PLUGIN_NOT_FOUND_VAULT);
            Bukkit.getPluginManager().disablePlugin(this);
        }

        if(Bukkit.getPluginManager().getPlugin("PlaceholderAPI") == null){
            ConsoleSender.sendConsoleMessage(Messages.PLUGIN_NOT_FOUND_PAPI);
        }else{
            Hook_PAPI = true;
        }
    }

    private void loadConfig(){
        C = new Configuration(this);
        C.initialize();
    }

    private void loadMessages(){
        try {
            new Messages().loadMessage();
        } catch (IOException | InvalidConfigurationException e) {
            throw new RuntimeException(e);
        }
    }

    private void deployShops(){
        new ShopPool();
    }

    private void regCommands(){
        CM = new CommandManager(C);
        Objects.requireNonNull(getCommand("mkes")).setExecutor(CM);
        Objects.requireNonNull(getCommand("mkes")).setTabCompleter(CM);
    }


    private void unregCommands(){
        Objects.requireNonNull(getCommand("mkes")).setExecutor(null);
        Objects.requireNonNull(getCommand("mkes")).setTabCompleter(null);
    }

    private void regListeners(){
        Bukkit.getPluginManager().registerEvents(new GUIListener(C), this);
    }

    private void unregListeners(){
        HandlerList.unregisterAll(this);
    }

    public static boolean isHookPAPI(){
        return Hook_PAPI;
    }

    public static Main getInstance(){
        return plugin;
    }

    public IConfiguration getConfiguration(){
        return C;
    }

    public ShopPool getShopPool(){
        return SP;
    }

}
