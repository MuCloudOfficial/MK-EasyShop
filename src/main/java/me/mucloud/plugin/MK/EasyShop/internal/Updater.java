package me.mucloud.plugin.MK.EasyShop.internal;

/**
 * 更新器模块
 * 更新器桑第一次上场有点有点害羞
 * 所以这个更新器桑只有在插件启动或相应命令执行时才会出现
 *
 * @since 1.1001
 */
public class Updater {

    private static final String Remote = "https://raw.githubusercontent.com/MuCloudOfficial/MK-EasyShop/master/src/main/resources/plugin.yml";
    private static Channel UpdateChannel;
    private static String InternalVersion;
    private static String LatestVersion;

    public Updater(Configuration c){
        InternalVersion = c.getInternalVersion();
    }

    public fetchRemoteVersion(){

    }

    private enum Channel{
        RELEASE,
        BETA,
    }

}
