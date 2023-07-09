package com.zhujunhui.domain;

import java.io.Serializable;

/**
 * for test
 * @author zhujunhui
 * @date 2023/7/10
 */
public class Person implements Serializable {
    private static final long serialVersionUID = -5810273244110995305L;
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
