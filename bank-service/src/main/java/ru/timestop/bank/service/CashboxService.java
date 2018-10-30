package ru.timestop.bank.service;

/**
 * @author t.i.m.e.s.t.o.p
 * @version 1.0.0
 * @since 22.10.2018
 */
public interface CashboxService {
    /**
     * create new cashbox and cashboxes account
     * @param description of cashbox
     * @return id of new cashbox
     */
    int createCashbox(String description);
}
