package com.fpm.reports;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Created by Kirill on 4/20/2017.
 */
@Component
public class B {

    @Autowired
    private A a;

    public B() {
        System.out.println("constructor B");
    }

    @PostConstruct
    private void init() {
        System.out.println("PostConstructor B");
    }


    public void useA(A aa) {
        assert this.a != null;
        assert this.a==aa;
    }

}
