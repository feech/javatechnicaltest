package com.fpm.reports;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;


/**
 * Created by Kirill on 4/20/2017.
 */

@Component
public class A {

    @Autowired
    private B b;

    public A() {
        System.out.println("constructor A");
    }

    @PostConstruct
    private void init() {
        System.out.println("PostConstructor A");
    }


    public void useB(B b) {
        assert this.b != null;
        assert this.b == b;
    }

}
