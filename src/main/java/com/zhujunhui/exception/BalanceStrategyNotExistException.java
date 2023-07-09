package com.zhujunhui.exception;

/**
 * @author zhujunhui
 * @date 2023/7/9
 */
public class BalanceStrategyNotExistException extends Exception {
    private static final long serialVersionUID = 2336931045864939356L;

    public BalanceStrategyNotExistException() {
        super();
    }

    /**
     * Constructs an {@code InterfaceNotRegisterException} with the specified
     * detail message.
     *
     * @param   msg   the detail message.
     */
    public BalanceStrategyNotExistException(String msg) {
        super(msg);
    }
}
