package com.zhujunhui.rpc;

import com.zhujunhui.exception.InterfaceExistedException;
import com.zhujunhui.exception.InterfaceNotRegisterException;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 接口映射器
 * @author zhujunhui
 * @date 2023/7/9
 */
public class Dispatcher {
    private static volatile Dispatcher INSTANCE;
    private final ConcurrentHashMap<String, Object> interfaceMap;

    private Dispatcher() {
        this.interfaceMap = new ConcurrentHashMap<>(32);
    }

    public static Dispatcher getInstance() {
        if (INSTANCE == null) {
            synchronized (Dispatcher.class) {
                if (INSTANCE == null) {
                    INSTANCE = new Dispatcher();
                }
            }
        }
        return INSTANCE;
    }

    public void register(String key, Object value) throws InterfaceExistedException {
        if (interfaceMap.containsKey(key)) {
            throw new InterfaceExistedException(key + " is registered, pleas do not register twice");
        }
        this.interfaceMap.put(key, value);
    }

    public Object get(String key) throws InterfaceNotRegisterException {
        Object interfaces = interfaceMap.get(key);
        if (interfaces == null) {
            throw new InterfaceNotRegisterException(key + " is not registered!");
        }
        return interfaces;
    }

    public void remove(String key) {
        interfaceMap.remove(key);
    }
}
