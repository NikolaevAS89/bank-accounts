package ru.timestop.bank.server.http.handler;

import com.sun.net.httpserver.HttpExchange;
import org.apache.log4j.Logger;
import ru.timestop.bank.provider.ProviderFactory;
import ru.timestop.html.HtmlUtil;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

/**
 * @author t.i.m.e.s.t.o.p
 * @version 1.0.0
 * @since 31.10.2018
 */
public class AccountHandler extends ResourceHandler {
    private static final Logger LOG = Logger.getLogger(AccountHandler.class);

    public AccountHandler() throws IOException {
        super("xml/create_account.xml");
    }

    public void handle(HttpExchange httpExchange) throws IOException {
        long elapse = System.currentTimeMillis();
        try {
            switch (httpExchange.getRequestMethod()) {
                case "POST":
                    httpExchange.sendResponseHeaders(200, 0);
                    Map<String, String> params = HtmlUtil.parseParams(HtmlUtil.read(httpExchange.getRequestBody(), 128));
                    int accountId = ProviderFactory.getAccountService().createAccount(params.get("description"));
                    if (LOG.isDebugEnabled()) {
                        LOG.debug("new account was created. New accounts id is " + accountId);
                    }
                    OutputStream os = httpExchange.getResponseBody();
                    os.write(("New account (" + accountId + ") was created").getBytes());
                    os.flush();
                    os.close();
                    break;
                default:
                    super.handle(httpExchange);
            }
            if (LOG.isDebugEnabled()) {
                elapse = System.currentTimeMillis() - elapse;
                LOG.debug("account handle. elapse : " + elapse);
            }
        } catch (Throwable e) {
            httpExchange.sendResponseHeaders(500, 0);
            OutputStream os = httpExchange.getResponseBody();
            os.write(e.getMessage().getBytes());
            os.flush();
            os.close();
        }
    }
}