package me.mucloud.mcplugin.MK.EasyShop.internal

import com.google.gson.JsonObject
import com.google.gson.JsonParser
import me.mucloud.mcplugin.MK.EasyShop.Main
import org.bukkit.ChatColor
import java.net.URL

object VersionChecker {

    private val CurrentVer: Version
    private var LatestVer: Version? = null
    private var enabled: Boolean = false // todo(Configuration Switcher)
    private var isRel: Boolean = false

    private val remoteGithub = "https://api.github.com/repos/MuCloudOfficial/MK-EasyShop/releases"

    init {
        val descVer = Main.getPluginDesc().version.split(".")
        CurrentVer = Version(isRel, MainVersion.get(descVer[0].toInt()), descVer[1].toInt(), descVer[2].toInt())
        Main.createAsyncTimerTsk(0, 20 * 60 * 60 * 6) {
            runCatching {
                val info = URL(remoteGithub).readText()
                JsonParser.parseString(info).asJsonArray.forEach {
                    val target = it as JsonObject
                    if (target["prerelease"].asBoolean == !isRel) {
                        val sp_ver = target["tag_name"].toString().split(".")
                        LatestVer = Version(sp_ver[0].toBoolean(), MainVersion.get(sp_ver[1]), sp_ver[2].drop(1).toInt(), sp_ver[3].toInt())
                    }
                }
            }.onFailure {
                Main.getSender().getLogger()?.warning("获取更新失败了")
            }
        }
    }

}

data class Version( // REL.VOIDLAND.V1.1
    private val isRel: Boolean,
    private val main: MainVersion,
    private val version: Int,
    private val internal: Int,
    private val dev: Int = 1
){

    operator fun compareTo(ver: Version): Int{
        return dev - ver.dev
    }

    override fun toString(): String = "${if(isRel) "Rel" else "Dev"}.$main.$version.$internal"

}

enum class MainVersion(
    private val id: Int,
    private val code: String,
    private val color: ChatColor
){

    STARRYSKY(1, "StarrySky", ChatColor.BLUE),
    SAKURAOCEAN(2, "SakuraOcean", ChatColor.LIGHT_PURPLE),
    VOIDLAND(3, "VoidLand", ChatColor.DARK_GRAY)
    ;

    override fun toString(): String = "$color$code"

    companion object{
        fun get(id: Int): MainVersion = entries.first { it.id == id }
        fun get(code: String): MainVersion = entries.first { it.code == code }
    }
}