package ru.timestop.bank.service;

import org.apache.log4j.Logger;
import ru.timestop.bank.entity.Account;

import javax.persistence.EntityManager;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author t.i.m.e.s.t.o.p
 * @version 1.0.0
 * @since 21.10.2018
 */
public class DocumentServiceImpl implements DocumentService {
    private final static Logger LOG = Logger.getLogger(DocumentServiceImpl.class);

    private AtomicLong count = new AtomicLong(0);

    private EntityManagerService entityManagerService;

    @Override
    public long createDocument(int debetId, int creditId, double value) {
        if (value <= 0) {
            throw new RuntimeException("Value of document can't be negative");
        }
        EntityManager entityManager = entityManagerService.getEntityManager();
        entityManager.getTransaction().begin();
        Account debet = entityManager.accountService.getUserAccount(debetId);
        entityManager.persist(debet);
        Account credit = accountService.getUserAccount(creditId);
        entityManager.persist(credit);
        double debet_value = debet.getAmount() + (debet.getType() == PassivAccount.TYPE_PASIV ? (-value) : (value));
        if (debet_value < 0) {
            entityManager.getTransaction().rollback();
            throw new RuntimeException("On " + debet.getId() + " account not enought money");
        }
        double credit_value = credit.getAmount() + (credit.getType() == PassivAccount.TYPE_ACTIV ? (-value) : (value));
        if (credit_value < 0) {
            entityManager.getTransaction().rollback();
            throw new RuntimeException("On " + credit.getId() + " account not enought money");
        }
        debet.setAmount(debet_value);
        credit.setAmount(credit_value);
        entityManager.flush();
        entityManager.getTransaction().commit();
        return count.incrementAndGet();
    }
}