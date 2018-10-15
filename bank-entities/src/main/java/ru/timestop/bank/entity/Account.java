package ru.timestop.bank.entity;

import javax.persistence.Column;
import javax.persistence.Id;

/**
 * @author t.i.m.e.s.t.o.p
 * @version 1.0.0
 * @since 14.10.2018
 */
public class Account {
    @Id
    @Column
    int id;
    @Column
    int balanceAccount;
    @Column
    int account;
    @Column
    double amount;

    public Account() {
    }
}
