package ru.timestop.bank.server.servlets;

import org.apache.log4j.Logger;
import ru.timestop.bank.server.provider.ProviderFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author t.i.m.e.s.t.o.p
 * @version 1.0.0
 * @since 23.10.2018
 */
@WebServlet(urlPatterns = {"/public/v1/bank/payments"})
public class PaymentsServlet extends HttpServlet {
    private static final Logger LOG = Logger.getLogger(PaymentsServlet.class);

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String debetId = req.getParameter("debetId");
        String creditId = req.getParameter("creditId");
        String summa = req.getParameter("summa");

        long paymentId = ProviderFactory.getDocumentService().createDocument(1, 2, 100.00);//TODO
        req.setAttribute("paymentId", paymentId);
        this.getServletContext().getRequestDispatcher("").forward(req, resp);//TODO
    }
}
