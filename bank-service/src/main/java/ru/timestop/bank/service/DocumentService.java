package ru.timestop.bank.service;

/**
 * @author t.i.m.e.s.t.o.p
 * @version 1.0.0
 * @since 14.10.2018
 */
public interface DocumentService {
    /**
     * create payment order
     *
     * @param debetId  account
     * @param creditId account
     * @param value
     * @return payment number
     */
    long createDocument(int debetId, int creditId, double value);
}
