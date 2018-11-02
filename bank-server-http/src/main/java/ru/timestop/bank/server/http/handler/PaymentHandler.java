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
public class PaymentHandler extends ResourceHandler {
    private static final Logger LOG = Logger.getLogger(PaymentHandler.class);

    public PaymentHandler() throws IOException {
        super("xml/create_payment.xml");
    }

    public void handle(HttpExchange httpExchange) throws IOException {
        long elapse = System.currentTimeMillis();
        try {
            switch (httpExchange.getRequestMethod()) {
                case "POST":
                    Map<String, String> params = HtmlUtil.parseParams(HtmlUtil.read(httpExchange.getRequestBody(), 128));
                    int debetId = Integer.parseInt(params.get("debetId"));
                    int creditId = Integer.parseInt(params.get("creditId"));
                    double summa = Double.parseDouble(params.get("summa"));
                    LOG.info("debetId=" + debetId + " creditId=" + creditId + " summa=" + summa);
                    long paymentId = ProviderFactory.getDocumentService().createDocument(debetId, creditId, summa);
                    httpExchange.sendResponseHeaders(200, 0);
                    OutputStream os = httpExchange.getResponseBody();
                    os.write(("New payment (" + paymentId + ") was created").getBytes());
                    os.flush();
                    os.close();
                    break;
                default:
                    super.handle(httpExchange);
            }
            if (LOG.isDebugEnabled()) {
                elapse = System.currentTimeMillis() - elapse;
                LOG.debug("payment handle. elapse : " + elapse);
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