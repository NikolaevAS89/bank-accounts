package ru.timestop.bank.provider;

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
            LOG.info("initialize EntityManagerFactory");
            entityManager = Persistence.createEntityManagerFactory("bank");
        } catch (Exception e) {
            LOG.error(e);
            throw new RuntimeException(e);
        }
    }

    private ProviderFactory() {
    }

    public static void close() {
        LOG.warn("Close entity manager");
        entityManager.close();
    }

    public static AccountService getAccountService() {
        if (LOG.isDebugEnabled()) {
            LOG.debug("get AccountService");
        }
        return new AccountServiceImpl(entityManager.createEntityManager());
    }

    public static CashboxService getCashboxService() {
        if (LOG.isDebugEnabled()) {
            LOG.debug("get CashboxService");
        }
        return new CashboxServiceImpl(entityManager.createEntityManager());
    }

    public static DocumentService getDocumentService() {
        if (LOG.isDebugEnabled()) {
            LOG.debug("get DocumentService");
        }
        return new DocumentServiceImpl(entityManager.createEntityManager());
    }
}
