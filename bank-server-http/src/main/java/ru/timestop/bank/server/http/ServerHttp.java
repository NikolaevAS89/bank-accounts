package ru.timestop.bank.server.http;

import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpServer;
import ru.timestop.bank.server.http.handler.MainHandler;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

/**
 * Created by nikolaev13-as on 31.10.2018.
 */
public class ServerHttp {


    public static void main(String[] args) throws IOException {
        HttpServer server = null;
        try {
            server = HttpServer.create();
            server.bind(new InetSocketAddress(8080), 0);

            HttpContext context = server.createContext("/public/v1/bank", new MainHandler());
            context.setAuthenticator(null);
            server.setExecutor(Executors.newCachedThreadPool());
            server.start();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (server != null) {
                server.stop(1000);
            }

        }
    }
}
