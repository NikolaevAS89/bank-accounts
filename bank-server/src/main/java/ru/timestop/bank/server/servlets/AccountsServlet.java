package ru.timestop.bank.server.servlets;

import org.apache.log4j.Logger;
import ru.timestop.bank.entity.Account;
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
@WebServlet(urlPatterns = {"/public/v1/bank/accounts"})
public class AccountsServlet extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(AccountsServlet.class);

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Account> accounts = ProviderFactory.getAccountService().getAll();
        req.setAttribute("accounts", accounts);
        this.getServletContext().getRequestDispatcher("/jsp/show_accounts.jsp").forward(req, resp);
    }
}
