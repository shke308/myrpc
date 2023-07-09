package com.zhujunhui;

import com.zhujunhui.rpc.Dispatcher;
import com.zhujunhui.rpc.protocol.RpcContent;
import com.zhujunhui.rpc.transport.ClientFactory;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.concurrent.CompletableFuture;

/**
 * @author zhujunhui
 * @date 2023/7/9
 */
public enum RpcProxy {
    /**
     * RpcProxy
     */
    INSTANCE;

    private Dispatcher dispatcher = Dispatcher.getInstance();

    @SuppressWarnings("unchecked")
    public <T> T getProxy(Class<T> interfaceInfo) {
        ClassLoader classLoader = interfaceInfo.getClassLoader();
        Class<?>[] interfaces  = {interfaceInfo};

        return (T) Proxy.newProxyInstance(classLoader, interfaces, (proxy, method, args) -> {
            Object res = null;
            Object sourceInterface = dispatcher.get(interfaceInfo.getName());
            if (sourceInterface == null) {
                // 远程连接
                String interfaceInfoName = interfaceInfo.getName();
                String methodName = method.getName();
                Class<?>[] parameterTypes = method.getParameterTypes();
                RpcContent.RpcContentBuilder rpcContentBuilder = new RpcContent.RpcContentBuilder();
                RpcContent rpcContent = rpcContentBuilder.interfaceName(interfaceInfoName)
                        .methodName(methodName)
                        .parameterTypes(parameterTypes)
                        .args(args).build();
                CompletableFuture<Object> resF = ClientFactory.transport(rpcContent);
                res = resF.get();
            } else {
                // 本地连接
                Class<?> clazz = sourceInterface.getClass();
                Method m = clazz.getMethod(method.getName(), method.getParameterTypes());
                res = m.invoke(sourceInterface, args);
            }
            return res;
        });
    }
}
