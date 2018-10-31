package ru.timestop.bank.server.http.handler;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import ru.timestop.entrance.utilites.IOUtil;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author t.i.m.e.s.t.o.p
 * @version 1.0.0
 * @since 31.10.2018
 */
public class MainHandler implements HttpHandler {

    private static final String PATH_MAIN_HTML = "main.html";
    private final String responceBody;

    public MainHandler() throws IOException {
        StringBuilder sb = new StringBuilder();
        InputStream fis = null;
        try {
            fis = this.getClass().getResourceAsStream(PATH_MAIN_HTML);
            byte[] buff = new byte[128];
            while (fis.read(buff) >= 0) {
                sb.append(buff);
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        } finally {
            IOUtil.closeQuiet(fis);
        }
        this.responceBody = sb.toString();
    }

    public void handle(HttpExchange httpExchange) throws IOException {
        String res = null;
        switch (httpExchange.getRequestMethod()) {
            case "GET":
                res = responceBody;
                break;
            default:
                res = "Method " + httpExchange.getRequestMethod() + " not supported";

        }
        byte[] bytes = res.getBytes();
        httpExchange.sendResponseHeaders(200, bytes.length);

        OutputStream os = httpExchange.getResponseBody();
        os.write(bytes);
        os.flush();
        os.close();

    }

}