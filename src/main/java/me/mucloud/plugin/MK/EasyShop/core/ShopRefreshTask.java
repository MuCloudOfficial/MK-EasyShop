package me.mucloud.plugin.MK.EasyShop.core;

import me.mucloud.plugin.MK.EasyShop.Main;
import me.mucloud.plugin.MK.EasyShop.internal.ConsoleSender;
import me.mucloud.plugin.MK.EasyShop.internal.Messages;
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
        refreshDate = Date.from(Instant.now().plusMillis(Interval *60 *60 *1000)).getTime();

        Enabled = true;
        PauseStatus = false;
    }

    /**
     *
     * 刷新计时器
     *
     * @param interval 当次循环的间隔时间（以小时计）
     * @return 返回当次循环结束的时间
     */
    public Date refreshInterval(long interval){
        Interval = interval;
        refreshDate = Date.from(Instant.now().plusMillis(Interval *60 *60 *1000)).getTime();
        return new Date(refreshDate);
    }

    private void deployTask(){
        Task = new BukkitRunnable(){
            @Override public void run() {
                if(TargetShop.toList().size() == 0){
                    ConsoleSender.sendConsoleMessage(""); //todo
                }
                Remaining = (refreshDate - new Date().getTime()) /1000 < 1 ?
                        0 : (refreshDate - new Date().getTime()) /1000;
                if(new Date().before(new Date(refreshDate))){
                    refreshNow();
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

    public void refreshNow(){
        refreshInterval(Interval);
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
