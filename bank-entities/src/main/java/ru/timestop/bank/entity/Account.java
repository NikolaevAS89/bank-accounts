package ru.timestop.bank.entity;

import ru.timestop.bank.dictionaries.AccountType;
import ru.timestop.entrance.utilites.JsonUtil;

import javax.persistence.*;

/**
 * @author t.i.m.e.s.t.o.p
 * @version 1.0.0
 * @since 14.10.2018
 */
@Table
@Entity(name = "account")
@NamedQueries({
        @NamedQuery(name = "Account.id.get", query = "select a " +
                " from account as a " +
                " where a.id = :id"),
        @NamedQuery(name = "Account.all.get", query = "select a " +
                " from account as a")})
public class Account {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @Column
    @Enumerated(EnumType.ORDINAL)
    private AccountType type;

    @Column
    private double amount;

    @Column
    private String description;

    public Account() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public AccountType getType() {
        return type;
    }

    public void setType(AccountType type) {
        this.type = type;
    }

    public String toString() {
        try {
            return JsonUtil.toJson(this);
        } catch (Exception e) {
            return "{id:" + id + ", description:\"" + description + "\"}";
        }
    }
}
