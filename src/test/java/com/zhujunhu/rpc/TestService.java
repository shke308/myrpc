package com.zhujunhu.rpc;

import com.zhujunhui.RpcService;
import org.junit.Test;

/**
 * @author zhujunhui
 * @date 2023/7/10
 */
public class TestService {

    @Test
    public void testStartServer() {
        RpcService instance = RpcService.getInstance();
        instance.startServer();
    }
}
