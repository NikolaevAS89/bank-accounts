package ru.timestop.bank.server.servlets;

import org.apache.log4j.Logger;
import ru.timestop.bank.exception.ServerWarningsException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * @author t.i.m.e.s.t.o.p
 * @version 1.0.0
 * @since 30.10.2018
 */
@WebServlet(urlPatterns = {"/public/v1/bank/error"})
public class ErrorServlet extends HttpServlet {
    private static final Logger LOG = Logger.getLogger(ErrorServlet.class);

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(LOG.isDebugEnabled()){
            LOG.debug("GET[/public/v1/bank/error]");
        }
        Throwable throwable = (Throwable) req.getAttribute("javax.servlet.error.exception");
        if (!(throwable instanceof ServerWarningsException)) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            throwable.printStackTrace(pw);
            LOG.error(sw.toString());
        }
        PrintWriter out = resp.getWriter();
        out.print(throwable.getMessage());
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if(LOG.isDebugEnabled()){
            LOG.debug("POST[/public/v1/bank/error]");
        }
        doGet(request, response);
    }
}
