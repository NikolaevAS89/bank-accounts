package ru.timestop.bank.server.http.handler;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.apache.log4j.Logger;
import org.xml.sax.SAXException;
import ru.timestop.bank.entity.Account;
import ru.timestop.bank.server.provider.ProviderFactory;
import ru.timestop.entrance.utilites.IOUtil;
import ru.timestop.xslt.TransformXSLT;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

/**
 * Created by nikolaev13-as on 01.11.2018.
 */
public class AccountsHandler implements HttpHandler {

    private static final Logger LOG = Logger.getLogger(AccountsHandler.class);

    public void handle(HttpExchange httpExchange) throws IOException {
        InputStream is = null;
        if (httpExchange.getRequestMethod().equals("GET")) {
            List<Account> accounts = ProviderFactory.getAccountService().getAll();
            httpExchange.sendResponseHeaders(200, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("<accounts>");
            for (Account account : accounts) {
                sb.append("<account><id>")
                        .append(account.getId())
                        .append("</id><type>")
                        .append(account.getType().name())
                        .append("</type><amount>")
                        .append(account.getAmount())
                        .append("</amount><description>")
                        .append(account.getDescription())
                        .append("</description></account>");
            }
            sb.append("</accounts>");
            InputStream data_stream = new ByteArrayInputStream(sb.toString().getBytes());
            InputStream xsl_stream = this.getClass().getClassLoader().getResourceAsStream("xsl/show_accounts.xsl");
            String result = null;
            try {
                result = TransformXSLT.transform(xsl_stream, data_stream);
            } catch (ParserConfigurationException | SAXException | TransformerException e) {
                result = e.getMessage();
            }
            System.out.println(result);
            is = new ByteArrayInputStream(result.getBytes());
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