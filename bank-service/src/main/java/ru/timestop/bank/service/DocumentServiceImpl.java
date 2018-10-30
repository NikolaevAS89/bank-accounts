package ru.timestop.bank.service;

import org.apache.log4j.Logger;
import ru.timestop.bank.dictionaries.AccountType;
import ru.timestop.bank.entity.Account;
import ru.timestop.bank.exception.ServerWarningsException;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author t.i.m.e.s.t.o.p
 * @version 1.0.0
 * @since 21.10.2018
 */
public class DocumentServiceImpl implements DocumentService {
    private final static Logger LOG = Logger.getLogger(DocumentServiceImpl.class);

    private AtomicLong count = new AtomicLong(0);

    private final EntityManager entityManager;

    public DocumentServiceImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public long createDocument(int debetId, int creditId, double value) {
        if (value <= 0.00) {
            throw new ServerWarningsException("Value of document can't be negative");
        }
        if (debetId == creditId) {
            throw new ServerWarningsException("Value of document can't be negative");
        }
        entityManager.getTransaction().begin();

        TypedQuery<Account> query = entityManager.createNamedQuery("Account.id.get", Account.class);
        query.setParameter("id", debetId);
        Account debet = query.getSingleResult();
        entityManager.persist(debet);
        query.setParameter("id", creditId);
        Account credit = query.getSingleResult();
        entityManager.persist(credit);
        double debet_value = debet.getAmount() + (debet.getType() == AccountType.PASSIVE ? (-value) : (value));
        if (debet_value < 0.00) {
            entityManager.getTransaction().rollback();
            throw new ServerWarningsException("On " + debet.getId() + " account not enought money");
        }
        double credit_value = credit.getAmount() + (credit.getType() == AccountType.ACTIVE ? (-value) : (value));
        if (credit_value < 0.00) {
            entityManager.getTransaction().rollback();
            throw new ServerWarningsException("On " + credit.getId() + " account not enought money");
        }
        debet.setAmount(debet_value);
        credit.setAmount(credit_value);
        entityManager.flush();
        entityManager.getTransaction().commit();
        return count.incrementAndGet();
    }
}
