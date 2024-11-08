package me.mucloud.mcplugin.MK.EasyShop.internal

import me.mucloud.mcplugin.MK.EasyShop.Main
import java.io.File

class Configuration(
    main: Main
){

    private val folder: File
    private val configFile: File

    private val type: DBType = DBType.YAML
    private var shopViewTitle = ""
    private var productViewTitle = ""

    init{
        folder = main.dataFolder
        configFile = folder.resolve("config.yml")
        if(!folder.exists()){
            folder.mkdir()
        }
        if(!configFile.exists()){
            main.saveResource("config.yml", true)
        }
        instance = this
    }

    companion object{
        lateinit var instance: Configuration
    }

    fun loadConfig(){

    }

    fun getShopViewTitle(): String = shopViewTitle
    fun getProductViewTitle(): String = productViewTitle

    enum class DBType{
        YAML, SQLITE, @Deprecated("尚未实现") MYSQL
    }

}