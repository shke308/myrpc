package com.zhujunhu.rpc;

import com.zhujunhui.RpcProxy;
import com.zhujunhui.domain.Person;
import com.zhujunhui.service.Car;
import org.junit.Test;

import java.io.IOException;

/**
 * @author zhujunhui
 * @date 2023/7/10
 */
public class TestClient {

    @Test
    public void testClient() {
        Car car = RpcProxy.INSTANCE.getProxy(Car.class);
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                Person person = car.getCarOwner("zhangsan", 16);
                System.out.println(person);
            }).start();
        }

        try {
            System.in.read();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
