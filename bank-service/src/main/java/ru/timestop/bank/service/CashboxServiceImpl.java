package ru.timestop.bank.service;

import org.apache.log4j.Logger;
import ru.timestop.bank.dictionaries.AccountType;
import ru.timestop.bank.entity.Account;
import ru.timestop.bank.entity.Cashbox;
import ru.timestop.bank.exception.ServerWarningsException;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;

/**
 * @author t.i.m.e.s.t.o.p
 * @version 1.0.0
 * @since 23.10.2018
 */
public class CashboxServiceImpl implements CashboxService {


    private final static Logger LOG = Logger.getLogger(CashboxServiceImpl.class);

    private EntityManager entityManager;

    public CashboxServiceImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public int createCashbox(String description) {
        long elapse = System.currentTimeMillis();
        Account newAccount = new Account();
        newAccount.setAmount(0.0);
        newAccount.setDescription("Account for cashbox [" + description + "]");
        newAccount.setType(AccountType.ACTIVE);
        Cashbox newCashbox = new Cashbox();
        newCashbox.setAccount(newAccount);
        newCashbox.setDescription(description);
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(newAccount);
            entityManager.persist(newCashbox);
            entityManager.flush();
            entityManager.getTransaction().commit();
        } catch (EntityExistsException e) {
            LOG.warn(e);
            entityManager.getTransaction().rollback();
            throw new ServerWarningsException(e);
        } catch (Throwable e) {
            LOG.error(e);
            try {
                entityManager.getTransaction().rollback();
            } catch (Throwable skip) {
                //SKIP
            }
            throw e;
        }
        if (LOG.isDebugEnabled()) {
            elapse = System.currentTimeMillis() - elapse;
            LOG.debug("get all accounts. elapse : " + elapse);
        }
        return newCashbox.getId();
    }
}
