package me.mucloud.plugin.MK.EasyShop.command;

import me.mucloud.plugin.MK.EasyShop.internal.Configuration;
import me.mucloud.plugin.MK.EasyShop.internal.Messages;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class CommandManager implements CommandExecutor, TabCompleter {

    private final Configuration C;
    private final List<String> SubCommands;

    public CommandManager(Configuration configuration){
        C = configuration;
        SubCommands = new ArrayList<>();
        SubCommands.add("gui");
        SubCommands.add("info");
    }

    public void sendInfo(CommandSender sender){
        if(Messages.Locale.equals("en_US")){
            sender.sendMessage("§7§l| §b§lMK§7§l-§6§lEasyShop      §6§lVer." + C.getVersion());
            sender.sendMessage("§7§l| §e§lAuthor: §7§l" + C.getAuthors());
            sender.sendMessage("§7§l| §e§lOpenSource: " + C.getWebsite());
            sender.sendMessage("§7§l| §7§m---------------------------------------------------------");
            sender.sendMessage("§7§l| §6/mkes gui     §eOpen Shop GUI");
            sender.sendMessage("§7§l| ");
            sender.sendMessage("§7§l| ");
            sender.sendMessage("§7§l| ");
            sender.sendMessage("§7§l| ");
            sender.sendMessage("§7§l| ");
            sender.sendMessage("§7§l| §7§m---------------------------------------------------------");
        }else{
            sender.sendMessage("§7§l| §b§lMK§7§l-§6§lEasyShop      " + C.getVersion() + "§6§l版本");
            sender.sendMessage("§7§l| §e§l作者: §7§l" + C.getAuthors());
            sender.sendMessage("§7§l| §e§l开源站: " + C.getWebsite());
            sender.sendMessage("§7§l| §7§m---------------------------------------------------------");
            sender.sendMessage("§7§l| §6/mkes gui      §e打开商店");
            sender.sendMessage("§7§l| ");
            sender.sendMessage("§7§l| ");
            sender.sendMessage("§7§l| ");
            sender.sendMessage("§7§l| ");
            sender.sendMessage("§7§l| ");
            sender.sendMessage("§7§l| §7§m---------------------------------------------------------");
        }
    }

    @Override public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String s, @NotNull String[] ss) {
        if(cmd.getName().equalsIgnoreCase("mkes")){
            if(ss.length == 0){
                sendInfo(sender);
            }else{
                switch (ss[0].toLowerCase()) {
                    case "info" -> { sendInfo(sender); return true; }
                    case "gui" -> { new gui(C, sender).execute(); return true; }
                }
            }
        }
        return true;
    }

    @Override public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String s, @NotNull String[] ss) {
        if(cmd.getName().equals("mkom")){
            return SubCommands;
        }
        return null;
    }

}
