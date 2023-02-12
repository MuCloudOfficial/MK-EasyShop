package me.mucloud.plugin.MK.EasyShop;

import me.mucloud.plugin.MK.EasyShop.command.CommandManager;
import me.mucloud.plugin.MK.EasyShop.core.ShopPool;
import me.mucloud.plugin.MK.EasyShop.internal.Configuration;
import me.mucloud.plugin.MK.EasyShop.internal.ConsoleSender;
import me.mucloud.plugin.MK.EasyShop.internal.Messages;
import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
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
        loadConfig();
        ConsoleSender.sendConsoleMessage("§b§lMADE IN STARRY SKY.");
        C.checkIntegrity();

        C.initialize();

        deployShops();
        regCommands();

    }

    @Override public void onDisable() {
        super.onDisable();
    }

    public void onReload(){ /*TODO*/ }

    public Configuration getConfiguration(){
        return C;
    }

    public ShopPool getShopPool(){
        return SP;
    }

    public void requestHookPlugins(){
        if(Bukkit.getPluginManager().getPlugin("Vault") != null){
            ConsoleSender.sendConsoleMessage(Messages.PLUGIN_HOOK_VAULT);
        }else{
            ConsoleSender.sendConsoleMessage(Messages.PLUGIN_NOT_FOUND_VAULT);
            Bukkit.getPluginManager().disablePlugin(this);
        }

        if(Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null){
            Hook_PAPI = true;
        }else{
            ConsoleSender.sendConsoleMessage(Messages.PLUGIN_NOT_FOUND_PAPI);
        }
    }

    public void loadConfig(){
        C = new Configuration(this);
    }

    public void loadMessages(){
        try {
            new Messages(this).loadMessage();
        } catch (IOException | InvalidConfigurationException e) {
            throw new RuntimeException(e);
        }
    }

    public void deployShops(){
        new ShopPool(this);
    }

    public void regCommands(){
        CM = new CommandManager(C);
        Objects.requireNonNull(getCommand("mkes")).setExecutor(CM);
        Objects.requireNonNull(getCommand("mkes")).setTabCompleter(CM);
    }

    public static boolean isHookPAPI(){
        return Hook_PAPI;
    }

    public static Main getInstance(){
        return plugin;
    }

}
