package me.mucloud.mcplugin.MK.EasyShop.internal.command

import me.mucloud.mcplugin.MK.EasyShop.Main
import me.mucloud.mcplugin.MK.EasyShop.core.ShopType
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

object CommandExecutor: CommandExecutor {

    private fun info(sender: CommandSender){
        sender.sendMessage("""
            §7§l
        """.trimIndent())
    }

    private fun addShop(sender: CommandSender, args: List<String>){
        val shopName = args[0]
        val shopType = ShopType.
        if(sender !is Player){
            Main.getSender().sendConsoleMessage("")
        }else{
            Main.getSender().sendMessage(sender, "")
        }
    }

    private fun test(sender: CommandSender, args: List<String>){
        if(sender !is Player){
            Main.getSender().sendConsoleMessage("")
        }else{
            Main.getSender().sendMessage(sender, "Preparing Plugin Logical Test...")
            info(sender)
            addShop(sender, listOf())
            Main.getSender().sendMessage(sender, "Logical Test Complete.")
        }
    }

    override fun onCommand(sender: CommandSender, cmd: Command, s: String, ss: Array<out String>): Boolean {
        if(cmd.name == "mkes"){
            if(ss.isEmpty()){
                info(sender)
            }else{
                when(ss[0].lowercase()){
                    "info" -> info(sender)
                    "addshop" -> addShop(sender, ss.drop(1))
                    "test" -> test(sender, ss.drop(1))
                }
            }
            return true
        }
        return false
    }

}