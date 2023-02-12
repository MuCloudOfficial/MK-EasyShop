package me.mucloud.plugin.MK.EasyShop.internal;

import me.mucloud.plugin.MK.EasyShop.Main;
import me.mucloud.plugin.MK.EasyShop.api.IConfiguration;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Configuration implements IConfiguration {

    // 关于插件
    // About Plugin.
    private final Main Main;
    public static final String Prefix = "§b§lMK§7§l-§6§lEasyShop";
    private List<String> Authors = new ArrayList<>();
    private String Website;
    private String Version;
    private String InternalVersion;
    private final List<String> ConfigVersionCompatible
             = List.of(
                    "StarrySky_C1.0"  // 第一主版本
    );

    // 文件相关
    // About Files.
    private final File ConfigFolder;
    private final File ConfigFile;

    // 设置相关
    // About Configurations.
    private String ConfigVersion;
    private String GuiTitleName;
    private String MessagePrefix;

    // 商店相关
    // About Shops.
    private boolean OnceSellEnabled;
    private Player MainAccount;

    public Configuration(Main main){
        Main = main;
        ConfigFolder = Main.getDataFolder();
        ConfigFile = new File(ConfigFolder, "config.yml");
    }

    public void initialize(){
        fetchPluginInfo();
        validateConfig();
        fetchConfig();
    }

    private void fetchPluginInfo(){
        try{
            Yaml y = new Yaml();
            Map<String, Object> map = y.loadAs(Main.getResource("plugin.yml"), HashMap.class);
            Version = Messages.isEN() ? map.get("version").toString() : map.get("versionCN").toString();
            InternalVersion = map.get("internalVersion").toString();
            Authors = (List<String>) map.get("authors");
            Website = map.get("website").toString();
        }catch (NullPointerException npe){
            npe.printStackTrace();
        }
    }

    @Override public void checkIntegrity(){
        if(!ConfigFolder.exists() || ConfigFolder.isFile()){
            ConfigFolder.mkdir();
        }
        if(!ConfigFile.exists() || ConfigFile.isDirectory()){
            Main.saveDefaultConfig();
        }
    }

    private void validateConfig(){
        if(!ConfigVersionCompatible.contains(Version)){
            ConsoleSender.warn(Messages.CONFIG_VERSION_ERROR);
            ConfigFile.renameTo(
                    new File(ConfigFolder, "config_" + new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()) + ".yml.bak")
            );
            Main.saveDefaultConfig();
        }
    }

    private void fetchConfig(){
        FileConfiguration fc = new YamlConfiguration();
        try {
            fc.load(ConfigFile);
            GuiTitleName = fc.getString("General.GUITitleName");
            MessagePrefix = fc.getString("General.Prefix");

            MainAccount = fc.get("Shop.SellAccount") == null ? null : Main.getServer().getPlayer(Objects.requireNonNull(fc.getString("Shop.SellAccount")));
            ConfigVersion = fc.getString("General.Version");
            OnceSellEnabled = fc.getBoolean("Shop.OnceSell", false);
        } catch (IOException | InvalidConfigurationException e) {
            throw new RuntimeException(e);
        }
    }

    @Override public File getConfigFolder(){
        return ConfigFolder;
    }

    @Override public String getVersion(){
        return Version;
    }

    @Override public List<String> getAuthors() {
        return Authors;
    }

    @Override public String getWebsite() {
        return Website;
    }

    @Override public String getInternalVersion() {
        return InternalVersion;
    }

    @Override public String getConfigVersion() {
        return ConfigVersion;
    }

    @Override public boolean isOnceSellEnabled() {
        return OnceSellEnabled;
    }

    @Override public String getGuiTitleName(){
        return GuiTitleName;
    }

    @Override public String getMessagePrefix(){
        return MessagePrefix;
    }

    @Override public Player getMainAccount() {
        return MainAccount;
    }

}
