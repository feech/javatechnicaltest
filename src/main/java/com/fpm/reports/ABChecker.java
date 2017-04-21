package com.fpm.reports;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Created by Kirill on 4/21/2017.
 */

@Component
public class ABChecker implements CommandLineRunner{
    @Autowired
    private A a;
    @Autowired
    private B b;

    @Override
    public void run(String... strings) throws Exception {
        a.useB(b);
        b.useA(a);
    }
}
