package me.mucloud.plugin.MK.EasyShop.internal;

import me.clip.placeholderapi.PlaceholderAPI;
import me.mucloud.plugin.MK.EasyShop.Main;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Messages {

    private final Main Plugin;
    private final File LangFile;
    public static String Locale;
    private static String Version;
    private final List<String> LangVersionCompatible
            = List.of(
            "StarrySky_L1.0"  // 第一主版本
    );
    public final List<String> KnownLocale
            = List.of(
                "zh_CN", "en_US", "Custom"
    );

    private final FileConfiguration fc = new YamlConfiguration();

    // 插件相关
    // About Plugin.
    public static String PLUGIN_ENABLING;
    public static String PLUGIN_DISABLING;
    public static String PLUGIN_ENABLED;
    public static String PLUGIN_DISABLED;
    public static String PLUGIN_HOOK_VAULT;
    public static String PLUGIN_NOT_FOUND_VAULT;
    public static String PLUGIN_HOOK_PAPI;
    public static String PLUGIN_NOT_FOUND_PAPI;
    public static String CONFIG_VERSION_ERROR;
    public static String CONFIG_CORRUPT;

    public static String SHOP_GUI_BUY;
    public static String SHOP_GUI_SELL;
    public static String SHOP_GUI_SKULL_TITLE;
    public static String SHOP_GUI_LIST_TITLE;
    public static List<String> SHOP_GUI_SKULL_LORE;

    public static String SHOP_REMAINING_PREFIX;

    public Messages(Main plugin) throws IOException, InvalidConfigurationException {
        Plugin = plugin;

        LangFile = new File(Plugin.getDataFolder(), "lang.yml");
        if(!LangFile.exists() || LangFile.isDirectory()){
            Plugin.saveResource("lang.yml", true);
        }

        fc.load(LangFile);
        Version = fc.getString("Version", null);
        if(Version == null || !LangVersionCompatible.contains(Version)){
            ConsoleSender.warn("插件语言文件版本错误，这并不会影响启动，但语言文件已经不可用并被备份，将使用内置的语言文件");
            LangFile.renameTo(
                    new File(Plugin.getDataFolder(), "lang_" + new SimpleDateFormat("YYYYMMdd_HHmmss").format(new Date()) + ".yml.bak")
            );
            Plugin.saveResource("lang.yml", true);
        }

        Locale = fc.getString("Locale", null);
        if(Locale == null || !KnownLocale.contains(Locale)){
            ConsoleSender.warn("插件语言设置有误，已自动设置为简体中文，这并不会影响启动");
            Locale = "zh_CN";
        }
    }

    public void loadMessage() throws IOException, InvalidConfigurationException {
        PLUGIN_ENABLING = convert(fc.getString(Locale + ".PLUGIN_ENABLING"));
        PLUGIN_DISABLING = convert(fc.getString(Locale + ".PLUGIN_DISABLING"));
        PLUGIN_ENABLED = convert(fc.getString(Locale + ".PLUGIN_ENABLED"));
        PLUGIN_DISABLED = convert(fc.getString(Locale + ".PLUGIN_DISABLED"));
        PLUGIN_HOOK_VAULT = convert(fc.getString(Locale + ".PLUGIN_HOOK_VAULT"));
        PLUGIN_NOT_FOUND_VAULT = convert(fc.getString(Locale + ".PLUGIN_NOT_FOUND_VAULT"));
        PLUGIN_HOOK_PAPI = convert(fc.getString(Locale + ".PLUGIN_HOOK_PAPI"));
        PLUGIN_NOT_FOUND_PAPI = convert(fc.getString(Locale + ".PLUGIN_NOT_FOUND_PAPI"));

        CONFIG_VERSION_ERROR = convert(fc.getString(Locale + ".CONFIG_VERSION_ERROR"));
        CONFIG_CORRUPT = convert(fc.getString(Locale + ".CONFIG_CORRUPT"));

        SHOP_GUI_BUY = convert(fc.getString(Locale + ".SHOP_GUI_BUY"));
        SHOP_GUI_SELL = convert(fc.getString(Locale + ".SHOP_GUI_SELL"));
        SHOP_GUI_SKULL_TITLE = convert(fc.getString(Locale + ".SHOP_GUI_SKULL_TITLE"));
        SHOP_GUI_SKULL_LORE = convert(fc.getStringList(Locale + ".SHOP_GUI_SKULL_LORE"));
        SHOP_GUI_LIST_TITLE = convert(fc.getString(Locale + ".SHOP_GUI_LIST_TITLE"));
        SHOP_REMAINING_PREFIX = convert(fc.getString(Locale + ".SHOP_REMAINING_PREFIX"));
    }

    public static List<String> convert(List<String> sl){
        for(String s : sl){
            sl.set(sl.indexOf(s), convert(s));
        }
        return sl;
    }
    public static String convert(String s){
        return ChatColor.translateAlternateColorCodes('&', s);
    }

    public static boolean isEN(){
        return Locale.equals("en_US");
    }

    public static String requestPlaceholder(OfflinePlayer p, String s){
        return Main.isHookPAPI() ? PlaceholderAPI.setPlaceholders(p, s) : s;
    }

    public static List<String> requestPlaceholder(OfflinePlayer p, List<String> sl){
        for(String s : sl){
            sl.set(sl.indexOf(s), requestPlaceholder(p, s));
        }
        return sl;
    }

}
