package ru.timestop.bank.service;

import org.apache.log4j.Logger;
import ru.timestop.bank.entity.Cashbox;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * @author t.i.m.e.s.t.o.p
 * @version 1.0.0
 * @since 23.10.2018
 */
public class CashboxServiceImpl implements CashboxService {


    private final static Logger LOG = Logger.getLogger(CashboxServiceImpl.class);

    private EntityManager entityManager; //TODO

    @Override
    public List<Cashbox> getAll() {
        TypedQuery<Cashbox> query = entityManager.createNamedQuery("Cashbox.all.get", Cashbox.class);
        return query.getResultList();
    }
}
