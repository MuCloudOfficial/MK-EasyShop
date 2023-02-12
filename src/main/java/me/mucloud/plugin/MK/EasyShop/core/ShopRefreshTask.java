package me.mucloud.plugin.MK.EasyShop.core;

import me.mucloud.plugin.MK.EasyShop.Main;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.time.Instant;
import java.util.Date;

public class ShopRefreshTask{

    private static final Main plugin = Main.getInstance();
    private Shop<?> TargetShop;
    private BukkitTask Task;
    private boolean Enabled;
    private boolean PauseStatus;
    private long refreshDate;
    private long Interval;
    private long Remaining;

    public ShopRefreshTask(Shop<?> shop) {
        TargetShop = shop;
        Interval = TargetShop.getRefreshInterval();
        refreshDate = Date.from(Instant.now().plusMillis(interval * 60 * 60 * 1000)).getTime();



        Enabled = true;
        PauseStatus = false;
    }

    public void refreshInterval(long interval){
        Interval = interval;
        refreshDate = Date.from(Instant.now().plusMillis(interval *60 *60 *1000)).getTime();
    }

    private void deployTask(){
        Task = new BukkitRunnable(){
            @Override public void run() {
                Remaining = (refreshDate - new Date().getTime()) /1000 < 1 ?
                        0 : (refreshDate - new Date().getTime()) /1000;
                if(new Date().before(new Date(refreshDate))){
                    TargetShop.refresh();
                    refreshInterval(Interval);
                }
            }
        }.runTaskTimerAsynchronously(plugin, 0L, 20L);
    }

    public void stop(){
        if(isEnabled()){
            Enabled = false;
            clear();
        }
    }

    public void pause(){
        if(isEnabled() && !PauseStatus){
            Task.cancel();
            PauseStatus = true;
        }
    }

    public void continueIt(){
        if(isEnabled() && PauseStatus){
            deployTask();
            PauseStatus = false;
        }
    }

    public String getRemain(){
        long day = Remaining /60 /60 /24;
        long hour = Remaining % (60 *60 *24);
        long minute = Remaining % (60 *60);
        long second = Remaining % 60;
        return day + "天" + hour + "时" + minute + "分" + second + "秒";
    }

    public boolean isEnabled() {
        return Enabled;
    }

    public void clear(){
        Enabled = false;
        PauseStatus = false;
        Task.cancel();
        TargetShop = null;
        Task = null;
    }
}
