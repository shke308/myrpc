package com.zhujunhui.rpc.transport;

import com.zhujunhui.config.PropertyMgr;
import com.zhujunhui.constant.RpcTransport;
import com.zhujunhui.exception.TransportNotExistException;
import com.zhujunhui.rpc.protocol.RpcContent;
import io.netty.handler.codec.http.HttpMethod;
import org.apache.http.HttpStatus;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;

/**
 * @author zhujunhui
 * @date 2023/7/9
 */
public class ClientFactory {
    private static ClientFactory INSTANCE;

    private ClientFactory() {
    }

    public static ClientFactory getInstance() {
        if (INSTANCE == null) {
            synchronized (ClientFactory.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ClientFactory();
                }
            }
        }
        return INSTANCE;
    }

    public CompletableFuture<Object> transport(RpcContent content) throws TransportNotExistException {
        RpcTransport transport = RpcTransport.getTransport(PropertyMgr.getString("rpc.communication.transport"));
        CompletableFuture<Object> res = new CompletableFuture<>();
        switch (transport) {
            case HTTP:
                httpTransport(content, res);
                break;
            default:
                throw new TransportNotExistException("transport \"" + transport.getValue() + "\" not support");
        }
        return res;
    }

    private static void httpTransport(RpcContent content, CompletableFuture<Object> res) {
        Object obj = null;
        try {
            URL url = new URL(Objects.requireNonNull(PropertyMgr.getString("rpc.connect.url")));
            HttpURLConnection connect = (HttpURLConnection)url.openConnection();
            connect.setRequestMethod(HttpMethod.POST.name());
            connect.setDoInput(true);
            connect.setDoOutput(true);
            try (OutputStream outputStream = connect.getOutputStream();
                 ObjectOutputStream out = new ObjectOutputStream(outputStream)) {
                out.writeObject(content);

                if (connect.getResponseCode() == HttpStatus.SC_OK) {
                    try (InputStream in = connect.getInputStream();
                         ObjectInputStream oin = new ObjectInputStream(in)) {
                        RpcContent myContent = (RpcContent) oin.readObject();
                        if (myContent != null) {
                            obj = myContent.getResponse();
                        }
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        res.complete(obj);
    }
}
