package ru.timestop.bank.server;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import ru.timestop.bank.provider.ProviderFactory;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.File;

/**
 * @author t.i.m.e.s.t.o.p
 * @version 1.0.0
 * @since 23.10.2018
 */
@WebListener()
public class ContextListener implements ServletContextListener {

    private final static Logger LOG = Logger.getLogger(ContextListener.class);

    @Override
    public void contextInitialized(ServletContextEvent event) {
        ServletContext context = event.getServletContext();
        String log4jConfigFile = context.getInitParameter("log4j-config-location");
        String fullPath = context.getRealPath("") + File.separator + log4jConfigFile;

        PropertyConfigurator.configure(fullPath);

        try {
            Class.forName(ProviderFactory.class.getName());
        } catch (ClassNotFoundException e) {
            LOG.error(e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        ProviderFactory.close();
    }
}