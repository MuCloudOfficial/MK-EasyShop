package me.mucloud.plugin.MK.EasyShop;

import me.mucloud.plugin.MK.EasyShop.api.IConfiguration;
import me.mucloud.plugin.MK.EasyShop.command.CommandManager;
import me.mucloud.plugin.MK.EasyShop.core.ShopPool;
import me.mucloud.plugin.MK.EasyShop.internal.Configuration;
import me.mucloud.plugin.MK.EasyShop.internal.ConsoleSender;
import me.mucloud.plugin.MK.EasyShop.internal.Messages;
import me.mucloud.plugin.MK.EasyShop.listener.GUIListener;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.util.Objects;

public class Main extends JavaPlugin{

    private static Main plugin;

    private static Configuration C;
    private static CommandManager CM;
    private static ShopPool SP;

    private static Economy Econ;
    private static boolean Hook_PAPI;

    @Override public void onEnable() {
        plugin = this;
        ConsoleSender.info("MADE IN STARRY SKY.");

        loadMessages();

        requestHookVault();
        requestHookPAPI();

        loadConfig();
        ConsoleSender.sendConsoleMessage(Configuration.Prefix + "§b§lMADE IN STARRY SKY.");
        ConsoleSender.sendConsoleMessage(Configuration.Prefix + Messages.PLUGIN_ENABLING);
        deployShops();
        regCommands();
        regListeners();

        ConsoleSender.sendConsoleMessage(Configuration.Prefix + Messages.PLUGIN_ENABLED);
    }

    @Override public void onDisable() {
        ConsoleSender.sendConsoleMessage(Configuration.Prefix + Messages.PLUGIN_DISABLING);

        unregCommands();
        unregListeners();

        ConsoleSender.sendConsoleMessage(Configuration.Prefix + Messages.PLUGIN_DISABLED);
    }

    public void onReload(){
        //TODO
    }

    public void requestHookVault(){
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            ConsoleSender.sendConsoleMessage(Configuration.Prefix + Messages.PLUGIN_NOT_FOUND_VAULT);
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            ConsoleSender.sendConsoleMessage(Configuration.Prefix + Messages.PLUGIN_NOT_COMPATIBLE_VAULT);
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
        Econ = rsp.getProvider();
    }

    public void requestHookPAPI(){
        if(Bukkit.getPluginManager().getPlugin("PlaceholderAPI") == null){
            ConsoleSender.sendConsoleMessage(Configuration.Prefix + Messages.PLUGIN_NOT_FOUND_PAPI);
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

    public Economy getEcon(){
        return Econ;
    }

}
