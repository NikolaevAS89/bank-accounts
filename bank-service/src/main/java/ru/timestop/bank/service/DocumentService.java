package ru.timestop.bank.service;

/**
 * @author t.i.m.e.s.t.o.p
 * @version 1.0.0
 * @since 14.10.2018
 */
public interface DocumentService {
    long createDocument(int debetId, int creditId, double value);
}
