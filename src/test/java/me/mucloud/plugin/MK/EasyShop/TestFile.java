package me.mucloud.plugin.MK.EasyShop;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TestFile {

    private static final List<String> l = new ArrayList<>();
    private static String s = "6";

    @Test public void test1(){
        l.add("1");
        l.add("2");
        l.add(s);

        System.out.println(l);
        s = "wow";
        System.out.println(l);
    }


}
