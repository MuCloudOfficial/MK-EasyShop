package me.mucloud.plugin.MK.EasyShop.api;

import org.bukkit.entity.Player;

import java.io.File;
import java.util.List;

public interface IConfiguration{

    void checkIntegrity();

    File getConfigFolder();

    // 插件相关
    // About Plugin.
    String getVersion();

    String getAuthors();

    String getWebsite();

    String getInternalVersion();

    // 设置相关
    // About Config.
    String getConfigVersion();

    String getMessagePrefix();

    String getGuiTitleName();

    Player getMainAccount();

    boolean isOnceSellEnabled();


}
