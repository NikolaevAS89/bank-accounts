package ru.timestop.bank.server.servlets;

import org.apache.log4j.Logger;
import ru.timestop.bank.entity.Cashbox;
import ru.timestop.bank.server.provider.ProviderFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author t.i.m.e.s.t.o.p
 * @version 1.0.0
 * @since 23.10.2018
 */
@WebServlet(urlPatterns = {"/public/v1/bank/cashboxes"})
public class CashboxesServlet extends HttpServlet {
    private static final Logger LOG = Logger.getLogger(PaymentsServlet.class);

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Cashbox> accounts = ProviderFactory.getCashboxService().getAll();
        req.setAttribute("accounts", accounts);
        this.getServletContext().getRequestDispatcher("").forward(req, resp);//TODO
    }
}
