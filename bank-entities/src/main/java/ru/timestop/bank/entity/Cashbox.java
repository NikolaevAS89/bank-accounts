package ru.timestop.bank.entity;

import ru.timestop.entrance.utilites.JsonUtil;

import javax.persistence.*;

/**
 * @author t.i.m.e.s.t.o.p
 * @version 1.0.0
 * @since 23.10.2018
 */
@Entity
@Table(name = "cashbox")
@NamedQueries({
        @NamedQuery(name = "Cashbox.all.get", query = "select c from cashbox c "),
        @NamedQuery(name = "Cashbox.id.get", query = "select c from cashbox c where c.id = :id")})
public class Cashbox {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @Column(name = "description")
    private String description;

    @Column(name = "account_id")
    @OneToOne(mappedBy = "id")
    private Account account;

    public Cashbox() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String toString() {
        try {
            return JsonUtil.toJson(this);
        } catch (Exception e) {
            return "{id:" + id + ", description:\"" + description + "\"}";
        }
    }
}
