package com.zhujunhui.service;

import com.zhujunhui.domain.Person;

/**
 * 测试接口
 * @author zhujunhui
 * @date 2023/7/10
 */
public interface Car {

    /**
     * 获取车主
     * @param name 名称
     * @param age  年龄
     * @return 车主信息
     */
    Person getCarOwner(String name, int age);
}
