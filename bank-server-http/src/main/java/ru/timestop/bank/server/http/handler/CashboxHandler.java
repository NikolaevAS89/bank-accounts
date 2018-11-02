package ru.timestop.bank.server.http.handler;

import com.sun.net.httpserver.HttpExchange;
import org.apache.log4j.Logger;
import ru.timestop.bank.provider.ProviderFactory;
import ru.timestop.html.HtmlUtil;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

/**
 * Created by nikolaev13-as on 01.11.2018.
 */
public class CashboxHandler extends ResourceHandler {

    private static final Logger LOG = Logger.getLogger(CashboxHandler.class);

    public CashboxHandler() throws IOException {
        super("xml/create_cashbox.xml");
    }

    public void handle(HttpExchange httpExchange) throws IOException {
        long elapse = System.currentTimeMillis();
        try {
            switch (httpExchange.getRequestMethod()) {
                case "POST":
                    httpExchange.sendResponseHeaders(200, 0);
                    Map<String, String> params = HtmlUtil.parseParams(HtmlUtil.read(httpExchange.getRequestBody(), 128));
                    int cashboxId = ProviderFactory.getCashboxService().createCashbox(params.get("description"));
                    if (LOG.isDebugEnabled()) {
                        LOG.debug("new cachbox was created. New cashboxes id is " + cashboxId);
                    }
                    OutputStream os = httpExchange.getResponseBody();
                    os.write(("New cashbox " + cashboxId + " was created").getBytes());
                    os.flush();
                    os.close();
                    break;
                default:
                    super.handle(httpExchange);
            }
            if (LOG.isDebugEnabled()) {
                elapse = System.currentTimeMillis() - elapse;
                LOG.debug("cashbox handle. elapse : " + elapse);
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