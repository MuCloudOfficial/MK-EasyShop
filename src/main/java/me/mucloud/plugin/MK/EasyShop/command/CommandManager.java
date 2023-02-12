package me.mucloud.plugin.MK.EasyShop.command;

import me.mucloud.plugin.MK.EasyShop.api.IConfiguration;
import me.mucloud.plugin.MK.EasyShop.internal.Configuration;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class CommandManager implements CommandExecutor, TabCompleter {

    private final IConfiguration IC;
    private final List<String> SubCommands;

    public CommandManager(Configuration configuration){
        IC = configuration;
        SubCommands = new ArrayList<>();
    }

    public void sendInfo(CommandSender sender, String Locale){
        if(Locale.equals("en_US")){
            sender.sendMessage("§7§l| " + Configuration.Prefix + "      §6§lVer." + IC.getVersion());
            sender.sendMessage("§7§l| §e§lAuthor: §7§l" + IC.getAuthors().toString().substring(1, IC.getAuthors().toString().length() - 2).replace(",", " "));
            sender.sendMessage("§7§l| §e§lOpenSource: " + IC.getWebsite());
            sender.sendMessage("§7§l| &7&m---------------------------------------------------------");
            sender.sendMessage("§7§l| ");
            sender.sendMessage("§7§l| ");
            sender.sendMessage("§7§l| ");
            sender.sendMessage("§7§l| ");
            sender.sendMessage("§7§l| ");
            sender.sendMessage("§7§l| ");
            sender.sendMessage("§7§l| &7&m---------------------------------------------------------");
        }else{
            sender.sendMessage("§7§l| " + Configuration.Prefix + "      " + IC.getVersion() + "§6§l版本");
            sender.sendMessage("§7§l| §e§l作者: §7§l" + IC.getAuthors().toString().substring(1, IC.getAuthors().toString().length() - 2).replace(",", " "));
            sender.sendMessage("§7§l| §e§l开源站: " + IC.getWebsite());
            sender.sendMessage("§7§l| &7&m---------------------------------------------------------");
            sender.sendMessage("§7§l| ");
            sender.sendMessage("§7§l| ");
            sender.sendMessage("§7§l| ");
            sender.sendMessage("§7§l| ");
            sender.sendMessage("§7§l| ");
            sender.sendMessage("§7§l| ");
            sender.sendMessage("§7§l| &7&m---------------------------------------------------------");
        }
    }

    @Override public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String s, @NotNull String[] ss) {
        return false;
    }

    @Override public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String s, @NotNull String[] ss) {
        if(cmd.getName().equals("mkom")){
            return SubCommands;
        }
        return null;
    }

}
