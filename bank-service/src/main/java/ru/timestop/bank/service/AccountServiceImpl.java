package ru.timestop.bank.service;

import org.apache.log4j.Logger;
import ru.timestop.bank.dictionaries.AccountType;
import ru.timestop.bank.entity.Account;
import ru.timestop.bank.exception.ServerWarningsException;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * @author t.i.m.e.s.t.o.p
 * @version 1.0.0
 * @since 21.10.2018
 */
public class AccountServiceImpl implements AccountService {

    private final static Logger LOG = Logger.getLogger(AccountServiceImpl.class);

    private EntityManager entityManager;

    public AccountServiceImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public int createAccount(String description) {
        long elapse = System.currentTimeMillis();
        Account newAccount = new Account();
        newAccount.setAmount(0.0);
        newAccount.setDescription(description);
        newAccount.setType(AccountType.PASSIVE);
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(newAccount);
            entityManager.flush();
            entityManager.getTransaction().commit();
        } catch (EntityExistsException e) {
            entityManager.getTransaction().rollback();
            throw new ServerWarningsException(e);
        } catch (Throwable e) {
            try {
                entityManager.getTransaction().rollback();
            } catch (Throwable skip) {
                //SKIP
            }
            throw e;
        }
        if (LOG.isDebugEnabled()) {
            elapse = System.currentTimeMillis() - elapse;
            LOG.debug("create new account. elapse : " + elapse);
        }
        return newAccount.getId();
    }

    @Override
    public List<Account> getAll() {
        long elapse = System.currentTimeMillis();
        TypedQuery<Account> query = entityManager.createNamedQuery("Account.all.get", Account.class);
        if (LOG.isDebugEnabled()) {
            elapse = System.currentTimeMillis() - elapse;
            LOG.debug("get all accounts. elapse : " + elapse);
        }
        return query.getResultList();
    }
}
