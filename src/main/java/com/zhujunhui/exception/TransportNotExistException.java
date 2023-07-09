package com.zhujunhui.exception;

/**
 * @author zhujunhui
 * @date 2023/7/9
 */
public class TransportNotExistException extends Exception{
    private static final long serialVersionUID = -1507657168134975815L;

    public TransportNotExistException() {
        super();
    }

    /**
     * Constructs an {@code InterfaceNotRegisterException} with the specified
     * detail message.
     *
     * @param   msg   the detail message.
     */
    public TransportNotExistException(String msg) {
        super(msg);
    }
}
