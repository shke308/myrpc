package com.zhujunhui.constant;

import com.zhujunhui.exception.TransportNotExistException;

/**
 * @author zhujunhui
 * @date 2023/7/9
 */
public enum RpcTransport {
    /**
     * http协议
     */
    HTTP("HTTP");

    String value;


    RpcTransport(String value) {
        this.value = value;
    }

    public static RpcTransport getTransport (String value) throws TransportNotExistException {
        for (RpcTransport rpcTransport : RpcTransport.values()) {
            if (rpcTransport.value.equals(value)) {
                return rpcTransport;
            }
        }
        throw new TransportNotExistException("transport \"" + value + "\" is not exist");
    }

    public String getValue() {
        return this.value;
    }
}