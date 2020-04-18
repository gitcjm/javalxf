package com.str.designpatterns.templatemethod;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

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
