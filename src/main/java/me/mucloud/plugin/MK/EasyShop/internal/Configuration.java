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
    private String Authors;
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
        checkIntegrity();
        try{
            validateConfig();
            fetchConfig();
        } catch (IOException | InvalidConfigurationException e) {
            throw new RuntimeException(e);
        }
    }

    private void fetchPluginInfo(){
        try{
            Yaml y = new Yaml();
            Map<String, Object> map = y.loadAs(Main.getResource("plugin.yml"), HashMap.class);
            Version = Messages.isEN() ? map.get("version").toString() : map.get("versionCN").toString();
            InternalVersion = map.get("internalVersion").toString();
            String authorString = map.get("authors").toString();
            Authors = authorString.toString().substring(1, authorString.length() - 1).replace(",", " ");
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

    private void validateConfig() throws IOException, InvalidConfigurationException {
        FileConfiguration fc = new YamlConfiguration();
        fc.load(ConfigFile);
        if(fc.get("General.Version") == null){
            ConsoleSender.warn(Messages.CONFIG_VERSION_ERROR);
            ConfigFile.renameTo(
                    new File(ConfigFolder, "config_" + new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()) + ".yml.bak")
            );
            Main.saveDefaultConfig();
        }
        ConfigVersion = fc.getString("General.Version");
        if(!ConfigVersionCompatible.contains(ConfigVersion)){
            ConsoleSender.warn(Messages.CONFIG_INCOMPATIBLE);
            ConfigFile.renameTo(
                    new File(ConfigFolder, "config_" + new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()) + ".yml.bak")
            );
            Main.saveDefaultConfig();
        }
    }

    private void fetchConfig() throws IOException, InvalidConfigurationException {
        FileConfiguration fc = new YamlConfiguration();

        fc.load(ConfigFile);
        GuiTitleName = Messages.convert(fc.getString("General.GUITitleName"), null, null);
        MessagePrefix = Messages.convert(fc.getString("General.Prefix"), null, null);

        MainAccount = fc.get("Shop.SellAccount") == null ? null : Main.getServer().getPlayer(Objects.requireNonNull(fc.getString("Shop.SellAccount")));
        OnceSellEnabled = fc.getBoolean("Shop.OnceSell", false);
    }

    @Override public File getConfigFolder(){
        return ConfigFolder;
    }

    public String getVersion(){
        return Version;
    }

    public String getAuthors() {
        return Authors;
    }

    public String getWebsite() {
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
