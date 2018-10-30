package ru.timestop.bank.service;

import ru.timestop.bank.entity.Account;

import java.util.List;

/**
 * @author t.i.m.e.s.t.o.p
 * @version 1.0.0
 * @since 14.10.2018
 */
public interface AccountService {

    /**
     * create new account with signed description
     *
     * @param description
     * @return id of new account
     */
    int createAccount(String description);

    /**
     * @return all accounts
     */
    List<Account> getAll();
}
