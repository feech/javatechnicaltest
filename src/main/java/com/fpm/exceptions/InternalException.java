package com.fpm.exceptions;

/**
 * Created by Kirill on 3/15/2017.
 */
public class InternalException extends RuntimeException {
    public InternalException() {
    }

    public InternalException(String s) {
        super(s);
    }

    public InternalException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public InternalException(Throwable throwable) {
        super(throwable);
    }

    public InternalException(String s, Throwable throwable, boolean b, boolean b1) {
        super(s, throwable, b, b1);
    }
}
