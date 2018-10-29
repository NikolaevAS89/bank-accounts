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

    private static final Logger LOG = Logger.getLogger(ProviderFactory.class);

    static {
        try {
            entityManager = Persistence.createEntityManagerFactory("bank");
            LOG.info("entityManager=" + entityManager);
        } catch (Exception e) {
            LOG.error(e);
            e.printStackTrace();
        }
    }

    private ProviderFactory() {
    }

    public static void close() {
        LOG.warn("Close entity manager");
        entityManager.close();
    }

    public static AccountService getAccountService() {
        return new AccountServiceImpl(entityManager.createEntityManager());
    }

    public static CashboxService getCashboxService() {
        return new CashboxServiceImpl(entityManager.createEntityManager());
    }

    public static DocumentService getDocumentService() {
        return new DocumentServiceImpl(entityManager.createEntityManager());
    }
}
