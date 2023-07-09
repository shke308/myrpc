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
    private String interfaceName;

    /**
     * 请求方法名称
     */
    private String methodName;

    /**
     * 请求参数类型
     */
    private Class<?>[] parameterTypes;

    /**
     * 方法参数
     */
    private Object[] args;

    /**
     * 返回的数据
     */
    private Object response;

    private RpcContent() {
    }
    public String getInterfaceName() {
        return interfaceName;
    }

    public String getMethodName() {
        return methodName;
    }

    public Class<?>[] getParameterTypes() {
        return parameterTypes;
    }

    public Object[] getArgs() {
        return args;
    }

    public Object getResponse() {
        return response;
    }

    public static class RpcContentBuilder {
        private RpcContent rpcContent;

        public RpcContentBuilder() {
            rpcContent = new RpcContent();
        }

        public RpcContentBuilder interfaceName (String interfaceName) {
            this.rpcContent.interfaceName = interfaceName;
            return this;
        }

        public RpcContentBuilder methodName (String methodName) {
            this.rpcContent.methodName = methodName;
            return this;
        }

        public RpcContentBuilder parameterTypes (Class<?>[] parameterTypes) {
            this.rpcContent.parameterTypes = parameterTypes;
            return this;
        }

        public RpcContentBuilder args (Object[] args) {
            this.rpcContent.args = args;
            return this;
        }

        public RpcContentBuilder response (Object response) {
            this.rpcContent.response = response;
            return this;
        }

        public RpcContent build() {
            return this.rpcContent;
        }
    }
}
