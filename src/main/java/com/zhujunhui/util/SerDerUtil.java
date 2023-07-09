package com.zhujunhui.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * 序列化工具
 * @author zhujunhui
 * @date 2023/7/9
 */
public class SerDerUtil {

    static ByteArrayOutputStream bos = new ByteArrayOutputStream();

    public synchronized static byte[] ser(Object msg) {
        bos.reset();
        try {
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
       return bos.toByteArray();
    }
}