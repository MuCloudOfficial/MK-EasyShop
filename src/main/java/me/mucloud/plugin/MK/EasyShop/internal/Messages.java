package me.mucloud.plugin.MK.EasyShop.internal;

import me.clip.placeholderapi.PlaceholderAPI;
import me.mucloud.plugin.MK.EasyShop.Main;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class Messages {

    private static final Main Plugin = Main.getInstance();
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
    public static String PLUGIN_NOT_COMPATIBLE_VAULT;
    public static String PLUGIN_HOOK_PAPI;
    public static String PLUGIN_NOT_FOUND_PAPI;

    public static String PLUGIN_FIND_UPDATE;
    public static String PLUGIN_UP_TO_DATE;
    public static String PLUGIN_UPDATE_FAIL;

    public static String CONFIG_VERSION_ERROR;
    public static String CONFIG_INCOMPATIBLE;
    public static String CONFIG_VALUE_CORRUPT;

    public static String SHOP_REMAINING_PREFIX;
    public static String SHOP_TYPE_BUY;
    public static String SHOP_TYPE_SELL;

    public static String SHOP_GUI_BUY;
    public static String SHOP_GUI_SELL;
    public static String SHOP_GUI_SKULL_TITLE;
    public static String SHOP_GUI_LIST_TITLE;
    public static List<String> SHOP_GUI_SKULL_LORE;
    public static String GUI_PREVIOUS;
    public static String GUI_NEXT;

    public Messages() throws IOException, InvalidConfigurationException {
        LangFile = new File(Plugin.getDataFolder(), "lang.yml");
        if(!LangFile.exists() || LangFile.isDirectory()){
            Plugin.saveResource("lang.yml", true);
        }

        fc.load(LangFile);
        Version = fc.getString("Version", null);
        if(Version == null || !LangVersionCompatible.contains(Version)){
            ConsoleSender.warn("插件语言文件版本错误，这并不会影响启动，但语言文件已经不可用并被备份，将使用内置的语言文件");
            LangFile.renameTo(
                    new File(Plugin.getDataFolder(), "lang_" + new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()) + ".yml.bak")
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
        PLUGIN_ENABLING = convert(fc.getString(Locale + ".PLUGIN_ENABLING"), null, null);
        PLUGIN_DISABLING = convert(fc.getString(Locale + ".PLUGIN_DISABLING"), null, null);
        PLUGIN_ENABLED = convert(fc.getString(Locale + ".PLUGIN_ENABLED"), null, null);
        PLUGIN_DISABLED = convert(fc.getString(Locale + ".PLUGIN_DISABLED"), null, null);
        PLUGIN_HOOK_VAULT = convert(fc.getString(Locale + ".PLUGIN_HOOK_VAULT"), null, null);
        PLUGIN_NOT_FOUND_VAULT = convert(fc.getString(Locale + ".PLUGIN_NOT_FOUND_VAULT"), null, null);
        PLUGIN_NOT_COMPATIBLE_VAULT = convert(fc.getString(Locale) + ".PLUGIN_NOT_COMPATIBLE_VAULT",null ,null);
        PLUGIN_HOOK_PAPI = convert(fc.getString(Locale + ".PLUGIN_HOOK_PAPI"), null, null);
        PLUGIN_NOT_FOUND_PAPI = convert(fc.getString(Locale + ".PLUGIN_NOT_FOUND_PAPI"), null, null);

        PLUGIN_FIND_UPDATE = convert(fc.getString(Locale + ".PLUGIN_FIND_UPDATE"), null, null);
        PLUGIN_UP_TO_DATE = convert(fc.getString(Locale + ".PLUGIN_UP_TO_DATE"), null, null);
        PLUGIN_UPDATE_FAIL = convert(fc.getString(Locale + ".PLUGIN_UPDATE_FAIL"), null, null);

        CONFIG_VERSION_ERROR = convert(fc.getString(Locale + ".CONFIG_VERSION_ERROR"), null, null);
        CONFIG_VALUE_CORRUPT = convert(fc.getString(Locale + ".CONFIG_VALUE_CORRUPT"), null, null);
        CONFIG_INCOMPATIBLE = convert(fc.getString(Locale + ".CONFIG_INCOMPATIBLE"), null, null);

        SHOP_REMAINING_PREFIX = convert(fc.getString(Locale + ".SHOP_REMAINING_PREFIX"), null, null);
        SHOP_TYPE_BUY = convert(fc.getString(Locale + ".SHOP_TYPE_BUY"), null, null);
        SHOP_TYPE_SELL = convert(fc.getString(Locale + ".SHOP_TYPE_SELL"), null ,null);

        SHOP_GUI_BUY = convert(fc.getString(Locale + ".SHOP_GUI_BUY"), null, null);
        SHOP_GUI_SELL = convert(fc.getString(Locale + ".SHOP_GUI_SELL"), null, null);
        SHOP_GUI_SKULL_TITLE = convert(fc.getString(Locale + ".SHOP_GUI_SKULL_TITLE"), null, null);
        SHOP_GUI_SKULL_LORE = convert(fc.getStringList(Locale + ".SHOP_GUI_SKULL_LORE"), null);
        SHOP_GUI_LIST_TITLE = convert(fc.getString(Locale + ".SHOP_GUI_LIST_TITLE"), null, null);
        GUI_NEXT = convert(fc.getString(Locale + ".GUI_NEXT"), null, null);
        GUI_PREVIOUS = convert(fc.getString(Locale + ".GUI_NEXT"), null, null);

    }

    public static List<String> convert(List<String> sl, @Nullable Map<String, String> replaceMap){
        if(replaceMap == null){
            for(String s : sl){
                sl.set(sl.indexOf(s), convert(s, null, null));
            }
        }else{
            for(String s : sl){
                for(Map.Entry<String, String> m : replaceMap.entrySet()){
                    sl.set(sl.indexOf(s), convert(s, m.getKey(), m.getValue()));
                }
            }
        }
        return sl;
    }

    public static String convert(String s, @Nullable String wantReplaced, @Nullable String replaced){
        s = wantReplaced != null && replaced != null && s.contains(wantReplaced) ? s.replace(wantReplaced, replaced) : s;
        return ChatColor.translateAlternateColorCodes('&', s);
    }

    public static boolean isEN(){
        return Locale.equals("en_US");
    }

    public static String requestPlaceholder(OfflinePlayer p, String s){
        s = s.replace("{player}", p.getPlayer().getDisplayName())
                .replace("{money}", String.valueOf(Plugin.getEcon().getBalance(p)));

        return Main.isHookPAPI() ? PlaceholderAPI.setPlaceholders(p, s) : s;
    }

    public static List<String> requestPlaceholder(OfflinePlayer p, List<String> sl){
        for(String s : sl){
            sl.set(sl.indexOf(s), requestPlaceholder(p, s));
        }
        return sl;
    }

}
