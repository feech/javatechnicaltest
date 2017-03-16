package com.fpm.exceptions;

/**
 * Created by Kirill on 3/15/2017.
 */
public class BaseException extends Exception {
    public BaseException() {
    }

    public BaseException(String s) {
        super(s);
    }

    public BaseException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public BaseException(Throwable throwable) {
        super(throwable);
    }

    public BaseException(String s, Throwable throwable, boolean b, boolean b1) {
        super(s, throwable, b, b1);
    }
}
