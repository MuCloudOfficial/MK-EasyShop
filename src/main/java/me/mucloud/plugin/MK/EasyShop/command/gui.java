package me.mucloud.plugin.MK.EasyShop.command;

import me.mucloud.plugin.MK.EasyShop.gui.MainView;
import me.mucloud.plugin.MK.EasyShop.gui.ViewPool;
import me.mucloud.plugin.MK.EasyShop.internal.Configuration;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class gui {

    private final Configuration C;
    private final CommandSender Sender;

    gui(Configuration c, CommandSender sender){
        C = c;
        Sender = sender;
    }

    public void execute(){
        if(Sender instanceof Player){
            ViewPool.toView((Player) Sender, new MainView(C, (Player) Sender));
        }else{
            Sender.sendMessage("需要玩家执行");
        }
    }

}
