package me.mucloud.mcplugin.MK.EasyShop.internal

import me.mucloud.mcplugin.MK.EasyShop.Main
import org.bukkit.Bukkit
import org.bukkit.command.ConsoleCommandSender
import org.bukkit.entity.Player
import java.util.logging.Logger

class MessageSender(
    main: Main
){

    private var ServerSender: ConsoleCommandSender? = null
    private var LOGGER: Logger? = null

    init{
        ServerSender = main.server.consoleSender
        LOGGER = Logger.getLogger("MK-EasyShop")
        ServerSender?.sendMessage("MADE IN VOID LAND")
        LOGGER?.fine("MADE IN VOID LAND")
    }

    fun getLogger(): Logger? = LOGGER

    fun broadcastMessage(msg: String) {
        Bukkit.broadcastMessage(msg)
    }

    fun sendMessage(player: Player, msg: String) {
        player.sendMessage(msg)
        // todo("PAPI支持")
    }

    fun sendConsoleMessage(msg: String){
        ServerSender?.sendMessage(msg)
    }

    fun close(){
        ServerSender?.sendMessage("MK-EasyShop Disabling.")
        LOGGER?.info("Plugin Disabling.")
    }

}