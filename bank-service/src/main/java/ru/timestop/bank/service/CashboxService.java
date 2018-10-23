package ru.timestop.bank.service;

import ru.timestop.bank.entity.Cashbox;

import java.util.List;

/**
 * @author t.i.m.e.s.t.o.p
 * @version 1.0.0
 * @since 22.10.2018
 */
public interface CashboxService {
    List<Cashbox> getAll();
}
