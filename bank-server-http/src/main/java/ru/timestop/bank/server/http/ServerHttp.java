package ru.timestop.bank.server.http;

import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import org.apache.log4j.Logger;
import ru.timestop.bank.provider.ProviderFactory;
import ru.timestop.bank.server.http.handler.*;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by nikolaev13-as on 31.10.2018.
 */
public class ServerHttp {

    private static final Logger LOG = Logger.getLogger(ServerHttp.class);

    private final static Map<String, HttpHandler> RESOURCES;
    private final static Properties PROPERTIES;
    private final ExecutorService executorService = Executors.newCachedThreadPool();

    static {
        //ProviderFactory
        RESOURCES = new HashMap<>();
        try {
            PROPERTIES = new Properties();
            PROPERTIES.load(ServerHttp.class.getClassLoader().getResourceAsStream("application.properties"));


            Class.forName(ProviderFactory.class.getName());

            RESOURCES.put("/css/bank.css", new ResourceHandler("css/bank.css"));
            RESOURCES.put("/js/bank.js", new ResourceHandler("js/bank.js"));
            RESOURCES.put("/js/main.js", new ResourceHandler("js/main.js"));
            RESOURCES.put("/xsl/show_accounts.xsl", new ResourceHandler("xsl/show_accounts.xsl"));
            RESOURCES.put("/public/v1/bank", new ResourceHandler("main.html"));
            RESOURCES.put("/public/v1/bank/account", new AccountHandler());
            RESOURCES.put("/public/v1/bank/cashbox", new CashboxHandler());
            RESOURCES.put("/public/v1/bank/payment", new PaymentHandler());
            RESOURCES.put("/public/v1/bank/accounts", new AccountsHandler());
        } catch (Exception e) {
            LOG.error(e);
            throw new RuntimeException(e);
        }
    }

    public void start() {
        try {
            long elapse = System.currentTimeMillis();
            HttpServer server = HttpServer.create();
            int port = Integer.parseInt(PROPERTIES.getProperty("port"));
            int connectionCnt = Integer.parseInt(PROPERTIES.getProperty("connection.count"));
            server.bind(new InetSocketAddress(port), connectionCnt);
            for (String path : RESOURCES.keySet()) {
                HttpContext context = server.createContext(path, RESOURCES.get(path));
                context.setAuthenticator(null);
                server.setExecutor(executorService);
            }
            if (LOG.isDebugEnabled()) {
                elapse = System.currentTimeMillis() - elapse;
                LOG.debug("account handle. elapse : " + elapse);
            }
            server.start();
        } catch (IOException e) {
            LOG.error(e.getMessage());
        }
    }

    public static void main(String[] args) {
        try {
            new ServerHttp().start();
        } catch (Throwable e) {
            LOG.error(e.getMessage());
        }
    }
}
