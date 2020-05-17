package com.str.designpatterns.templatemethod;

import org.junit.Test;

public class AbstractSettingTest {

    //AbstractSetting setting = new LocalSetting();
    AbstractSetting setting = new RedisSetting();

    @Test
    public void getSetting() {
        System.out.println(setting.getSetting("name"));
        System.out.println(setting.getSetting("gender"));
        System.out.println(setting.getSetting("phone"));
    }

}
