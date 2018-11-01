package ru.timestop.bank.server.http.handler;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import ru.timestop.entrance.utilites.IOUtil;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by nikolaev13-as on 01.11.2018.
 */
public class ResourceHandler implements HttpHandler {

    private final String fileName;


    public ResourceHandler(String fileName) throws IOException {
        this.fileName = fileName;
    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        InputStream is = null;
        if (httpExchange.getRequestMethod().equals("GET")) {
            httpExchange.sendResponseHeaders(200, 0);
            is = this.getClass().getClassLoader().getResourceAsStream(fileName);
        } else {
            httpExchange.sendResponseHeaders(405, 0);
            is = new ByteArrayInputStream(("Method \"" + httpExchange.getRequestMethod() + "\" not found").getBytes());
        }
        OutputStream os = httpExchange.getResponseBody();
        IOUtil.redirectStream(is, os, 128);
        os.flush();
        os.close();
    }
}
