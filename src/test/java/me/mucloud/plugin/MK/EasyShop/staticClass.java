package me.mucloud.plugin.MK.EasyShop;

public class staticClass {

    private static String name;

    public staticClass(String name){
        this.name = name ;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        staticClass.name = name;
    }



}
