package ru.timestop.bank.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author t.i.m.e.s.t.o.p
 * @version 1.0.0
 * @since 14.10.2018
 */
@Entity
public class BalanceAccount {
    @Id
    @Column
    int account;
    @Column
    char type;
    @Column
    String description;

    public BalanceAccount() {
    }
}
