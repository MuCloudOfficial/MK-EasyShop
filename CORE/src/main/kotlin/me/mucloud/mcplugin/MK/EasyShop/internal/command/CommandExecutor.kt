package me.mucloud.mcplugin.MK.EasyShop.internal.command

import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender

object CommandExecutor: CommandExecutor {

    private fun info(sender: CommandSender){
        sender.sendMessage("""
            
        """.trimIndent())
    }

    private fun addShop(){

    }

    override fun onCommand(sender: CommandSender, cmd: Command, s: String, ss: Array<out String>): Boolean {
        if(cmd.name == "mkes"){
            if(ss.isEmpty()){
                info(sender)
            }else{
                if(ss.size == 1){
                    if(ss[0] == "test1"){

                    }
                }
            }
            return true
        }
        return false
    }

}