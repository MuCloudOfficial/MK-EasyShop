package me.mucloud.mcplugin.MK.EasyShop

import me.mucloud.mcplugin.MK.EasyShop.internal.Configuration
import me.mucloud.mcplugin.MK.EasyShop.internal.MessageSender
import me.mucloud.mcplugin.MK.EasyShop.internal.command.CommandExecutor
import org.bukkit.Bukkit
import org.bukkit.event.HandlerList
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.scheduler.BukkitRunnable
import org.bukkit.scheduler.BukkitTask
import org.bukkit.util.Consumer

class Main : JavaPlugin() {

    private lateinit var MessageSender: MessageSender
    private lateinit var Configuration: Configuration

    override fun onEnable(){
        instance = this
        MessageSender = MessageSender(this)
        Configuration = Configuration(this)
        getCommand("mkes")?.setExecutor(CommandExecutor)
    }

    override fun onDisable(){
        MessageSender.close()
        HandlerList.unregisterAll(this)
        getCommand("mkes")?.setExecutor(null)
        Bukkit.getScheduler().cancelTasks(this)
    }

    companion object{
        private lateinit var instance: Main

        fun getSender(): MessageSender = instance.MessageSender
        fun getConfiguration(): Configuration = instance.Configuration
        fun getPluginDesc() = instance.description
        fun createAsyncTimerTsk(delay: Long, period: Long, tsk: (BukkitTask) -> Unit) {
            Bukkit.getScheduler().runTaskTimerAsynchronously(instance, tsk, delay, period)
        }

        fun createAsyncTsk(tsk: Runnable){
            Bukkit.getScheduler().runTaskAsynchronously(instance, tsk)
        }

    }
}
