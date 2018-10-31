package ru.timestop.bank.server.http.handler;


import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;


/**
 * @author t.i.m.e.s.t.o.p
 * @version 1.0.0
 * @since 31.10.2018
 */
public class AccountHandler implements HttpHandler {
    public void handle(HttpExchange httpExchange) throws IOException {
        StringBuilder res = new StringBuilder();
        switch (httpExchange.getRequestMethod()) {
            case "POST":
                break;
            case "GET":
                break;
            default:
        }
        byte[] bytes = "asdf".getBytes();
        httpExchange.sendResponseHeaders(200, bytes.length);

        OutputStream os = httpExchange.getResponseBody();
        os.write(bytes);
        os.flush();
        os.close();
    }

}
