package com.zhujunhui.service;

import com.zhujunhui.domain.Person;

/**
 * 测试接口
 * @author zhujunhui
 * @date 2023/7/10
 */
public class MayCar implements Car {

    @Override
    public Person getCarOwner(String name, int age) {
        return new Person(name, age);
    }
}
