package com.zhujunhui.exception;

/**
 * @author zhujunhui
 * @date 2023/7/9
 */
public class InterfaceNotRegisterException extends Exception {

    private static final long serialVersionUID = -8198992052882719408L;

    public InterfaceNotRegisterException() {
        super();
    }

    /**
     * Constructs an {@code InterfaceNotRegisterException} with the specified
     * detail message.
     *
     * @param   msg   the detail message.
     */
    public InterfaceNotRegisterException(String msg) {
        super(msg);
    }

}
