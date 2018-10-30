package ru.timestop.bank.server.servlets;

import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author t.i.m.e.s.t.o.p
 * @version 1.0.0
 * @since 27.10.2018
 */
@WebServlet(urlPatterns = {"/public/v1/bank"})
public class BankServlet extends HttpServlet {
    private static final Logger LOG = Logger.getLogger(BankServlet.class);

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        if(LOG.isDebugEnabled()){
            LOG.debug("GET[/public/v1/bank]");
        }
        this.getServletContext().getRequestDispatcher("/jsp/main.jsp").forward(req, resp);
    }


}
