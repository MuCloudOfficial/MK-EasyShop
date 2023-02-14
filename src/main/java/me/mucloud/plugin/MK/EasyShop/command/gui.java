package me.mucloud.plugin.MK.EasyShop.command;

import me.mucloud.plugin.MK.EasyShop.internal.Configuration;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class gui {

    private Configuration C;
    private CommandSender Sender;

    gui(Configuration c, CommandSender sender){
        C = c;
        Sender = sender;
    }

    public void execute(){
        if(Sender instanceof Player){
            // todo
        }else{
            Sender.sendMessage("需要玩家执行");
        }
    }

}
