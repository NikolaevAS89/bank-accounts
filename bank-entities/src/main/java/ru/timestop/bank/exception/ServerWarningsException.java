package ru.timestop.bank.exception;

/**
 * @author t.i.m.e.s.t.o.p
 * @version 1.0.0
 * @since 30.10.2018
 */
public class ServerWarningsException extends RuntimeException {

    public ServerWarningsException(String message) {
        super(message);
    }

    public ServerWarningsException(Throwable e) {
        super(e);
    }
}
