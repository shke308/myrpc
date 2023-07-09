package com.zhujunhui.config;

import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

/**
 * @author zhujunhui
 * @date 2023/7/9
 */
public class PropertyMgr {
    private static Properties properties = new Properties();

    static {
        ClassLoader classLoader = PropertyMgr.class.getClassLoader();
        try {
            properties.load(classLoader.getResourceAsStream("rpcconfig.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String getString(String key) {
        if (properties == null) {
            return null;
        }
        return properties.getProperty(key);
    }

    public static int getInt(String key) {
        return Integer.parseInt(Objects.requireNonNull(getString(key)));
    }

    public static Object get(String key) {
        if (properties == null) {
            return null;
        }
        return properties.get(key);
    }
}
