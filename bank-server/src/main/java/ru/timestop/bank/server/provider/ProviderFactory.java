package ru.timestop.bank.server.provider;

import org.apache.log4j.Logger;
import ru.timestop.bank.service.*;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * @author t.i.m.e.s.t.o.p
 * @version 1.0.0
 * @since 23.10.2018
 */
public class ProviderFactory {
    private static EntityManagerFactory entityManager;

    private static final Logger LOG = Logger.getLogger(ProviderFactory.class.getName());

    static {
        try {
            entityManager = Persistence.createEntityManagerFactory("store");
            LOG.info("entityManager=" + entityManager);
        } catch (Exception e) {
            LOG.error(e);
        }
    }

    private ProviderFactory() {
    }

    public static void close() {
        entityManager.close();
    }

    public static AccountService getAccountService() {
        return new AccountServiceImpl();
    }

    public static CashboxService getCashboxService() {
        return new CashboxServiceImpl();
    }

    public static DocumentService getDocumentService() {
        return new DocumentServiceImpl();
    }
}
