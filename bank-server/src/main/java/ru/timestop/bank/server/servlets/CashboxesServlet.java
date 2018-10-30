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
@WebServlet(urlPatterns = {"/public/v1/bank/cashbox"})
public class CashboxesServlet extends HttpServlet {
    private static final Logger LOG = Logger.getLogger(CashboxesServlet.class);

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (LOG.isDebugEnabled()) {
            LOG.debug("GET[/public/v1/bank/cashbox]");
        }
        this.getServletContext().getRequestDispatcher("/jsp/create_cashbox.jsp").forward(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (LOG.isDebugEnabled()) {
            LOG.debug("POST[/public/v1/bank/cashbox]");
        }
        String description = req.getParameter("description");
        int cashboxId = ProviderFactory.getCashboxService().createCashbox(description);
        if (LOG.isDebugEnabled()) {
            LOG.debug("new cachbox was created. New cashbox id is " + cashboxId);
        }
        PrintWriter out = resp.getWriter();
        out.print("New cashbox " + cashboxId + " was created");
    }
}
