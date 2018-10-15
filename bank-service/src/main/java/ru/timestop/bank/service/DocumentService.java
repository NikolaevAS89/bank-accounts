package ru.timestop.bank.service;

import ru.timestop.bank.entity.Account;

/**
 * @author t.i.m.e.s.t.o.p
 * @version 1.0.0
 * @since 14.10.2018
 */
public interface DocumentService {
    long createDocument(Account debet, Account credit, double value);
}
