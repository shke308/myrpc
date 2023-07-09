package com.zhujunhui.constant;

import com.zhujunhui.exception.BalanceStrategyNotExistException;

/**
 * @author zhujunhui
 * @date 2023/7/9
 */
public enum BalanceStrategy {
    /**
     * 随机
     */
    RANDOM(0),
    /**
     * 轮训
     */
    POLL(1);

    int value;

    BalanceStrategy (int value) {
        this.value = value;
    }

    public static BalanceStrategy getStrategy (int value) throws BalanceStrategyNotExistException {
        BalanceStrategy[] values = BalanceStrategy.values();
        for (BalanceStrategy strategy : values) {
            if(strategy.value == value) {
                return strategy;
            }
        }
        throw new BalanceStrategyNotExistException("strategy " + value + " not exist, please choose strategy in [0, " + (values.length - 1) + " ]" );
    }

}
