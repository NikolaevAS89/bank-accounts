package ru.timestop.bank.server.servlets;

import org.apache.log4j.Logger;
import ru.timestop.bank.server.provider.ProviderFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author t.i.m.e.s.t.o.p
 * @version 1.0.0
 * @since 23.10.2018
 */
@WebServlet(urlPatterns = {"/public/v1/bank/payments"})
public class PaymentsServlet extends HttpServlet {
    private static final Logger LOG = Logger.getLogger(PaymentsServlet.class);

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.getServletContext().getRequestDispatcher("/jsp/create_payment.jsp").forward(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int debetId = Integer.parseInt(req.getParameter("debetId"));
        int creditId = Integer.parseInt(req.getParameter("creditId"));
        double summa = Double.parseDouble(req.getParameter("summa"));
        try {
            LOG.info("___________________________________");
            LOG.info("debetId=" + debetId + " creditId=" + creditId + " summa=" + summa);
            LOG.info("___________________________________");
            long paymentId = ProviderFactory.getDocumentService().createDocument(debetId, creditId, summa);
            req.setAttribute("paymentId", paymentId);
            LOG.error("New payment is " + paymentId);
            PrintWriter out = resp.getWriter();
            out.print("New payment (" + paymentId + ") was created");
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}
