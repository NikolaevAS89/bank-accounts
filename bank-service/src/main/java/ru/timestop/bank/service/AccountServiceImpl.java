package ru.timestop.bank.service;

import org.apache.log4j.Logger;
import ru.timestop.bank.dictionaries.AccountType;
import ru.timestop.bank.entity.Account;

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

    private EntityManager entityManager; //TODO

    public AccountServiceImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public int createAccount(String description) {
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
            LOG.warn(e);
            entityManager.getTransaction().rollback();
        } catch (Throwable e) {
            LOG.error(e);
            try {
                entityManager.getTransaction().rollback();
            } catch (Throwable skip) {
                //SKIP
            }
        }
        return newAccount.getId();
    }

    @Override
    public List<Account> getAll() {
        TypedQuery<Account> query = entityManager.createNamedQuery("Account.all.get", Account.class);
        return query.getResultList();
    }
}
