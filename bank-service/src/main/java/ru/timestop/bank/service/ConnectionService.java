package ru.timestop.bank.service;

import java.sql.Connection;

/**
 * @author t.i.m.e.s.t.o.p
 * @version 1.0.0
 * @since 14.10.2018
 */
public interface ConnectionService {
    Connection createConnection();
}