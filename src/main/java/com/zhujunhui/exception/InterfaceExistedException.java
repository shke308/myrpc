package com.zhujunhui.exception;

/**
 * @author zhujunhui
 * @date 2023/7/9
 */
public class InterfaceExistedException extends Exception {
    public InterfaceExistedException() {
        super();
    }

    /**
     * Constructs an {@code InterfaceNotRegisterException} with the specified
     * detail message.
     *
     * @param   msg   the detail message.
     */
    public InterfaceExistedException(String msg) {
        super(msg);
    }
}
