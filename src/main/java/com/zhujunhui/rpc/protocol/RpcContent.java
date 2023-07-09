package com.zhujunhui.rpc.protocol;

import java.io.Serializable;

/**
 * @author zhujunhui
 * @date 2023/7/9
 */
public class RpcContent implements Serializable {

    private static final long serialVersionUID = 7747000312663283166L;

    /**
     * 请求接口名称
     */
    String name;

    /**
     * 请求方法名称
     */
    String methodName;

    /**
     * 请求参数类型
     */
    Class<?>[] parameterTypes;

    /**
     * 方法参数
     */
    Object[] args;

    /**
     * 返回的数据
     */
    Object response;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Class<?>[] getParameterTypes() {
        return parameterTypes;
    }

    public void setParameterTypes(Class<?>[] parameterTypes) {
        this.parameterTypes = parameterTypes;
    }

    public Object[] getArgs() {
        return args;
    }

    public void setArgs(Object[] args) {
        this.args = args;
    }

    public Object getResponse() {
        return response;
    }

    public void setResponse(Object response) {
        this.response = response;
    }
}
