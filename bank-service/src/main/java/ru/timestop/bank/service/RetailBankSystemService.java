package ru.timestop.bank.service;

/**
 * @author t.i.m.e.s.t.o.p
 * @version 1.0.0
 * @since 14.10.2018
 */
public interface RetailBankSystemService {
    int createUserAccount();

    int getUserAccount(int account);

    long refillUserAccount(int id, double value);

    long withdrawalsUserAccount(int id, double value);

    long payment(int debetId, int creditId, double value);
}
