package com.zhujunhui.exception;

/**
 * @author zhujunhui
 * @date 2023/7/10
 */
public class TransportNotSupportException extends Exception {

    private static final long serialVersionUID = -8676424664206715938L;

    public TransportNotSupportException() {
        super();
    }

    /**
     * Constructs an {@code InterfaceNotRegisterException} with the specified
     * detail message.
     *
     * @param   msg   the detail message.
     */
    public TransportNotSupportException(String msg) {
        super(msg);
    }
}
