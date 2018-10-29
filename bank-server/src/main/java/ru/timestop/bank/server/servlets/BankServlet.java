package ru.timestop.bank.server.servlets;

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

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        this.getServletContext().getRequestDispatcher("/jsp/main.jsp").forward(req, resp);
    }


}
