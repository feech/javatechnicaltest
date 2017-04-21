package com.fpm.domain;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Random;

/**
 * Created by Kirill on 4/20/2017.
 */
public class BigDecimalTest {


    @Test
    public void dou() {
        Random random = new Random(1238);

        double[] n = new double[1_00_000_000];
        for (int i = 0; i < 1_00_000_000; i++) {
            n[i] = random.nextDouble();
        }

        long l = System.currentTimeMillis();

        double sum = Arrays.stream(n)
                .parallel()
                .sum();

        System.out.println(sum);
        System.out.println("summation of doubles in long array: "+(System.currentTimeMillis() - l));

    }

    @Test
    public void dou_nomem() {
        long l = System.currentTimeMillis();
        for (int i = 0; i < 1_00_000_000; i++) {
            double v = .0 + 10.;
            assert v>0;
        }
        System.out.println("summation of many doubles "+ (System.currentTimeMillis() - l));
    }

    @Test
    public void dou_reduce() {
        Random random = new Random(1238);

        double[] n = new double[1_00_000_000];
        for (int i = 0; i < 1_00_000_000; i++) {
            n[i] = random.nextDouble();
        }

        long l = System.currentTimeMillis();

        double sum = Arrays.stream(n)
                .parallel()
                .reduce(0.0, (x, y) -> x + y);

        System.out.println("reduction of doubles in array: "+(System.currentTimeMillis() - l));

    }

    @Test
    public void BuDec() {

        Random random = new Random(1238);
        BigDecimal[] n = new BigDecimal[1_00_000_000];
        for (int i = 0; i < 1_00_000_000; i++) {
            n[i] = new BigDecimal(String.format("%.3f",random.nextDouble()));
            n[i] = new BigDecimal(random.nextDouble());
        }

        long l = System.currentTimeMillis();

        double sum =
                Arrays.stream(n)
                        .parallel()
                        .reduce(BigDecimal.ZERO, BigDecimal::add).doubleValue();

        System.out.println("summation of BigDecimal in long array"+(System.currentTimeMillis() - l));

    }
    @Test
    public void BuDec_nomem() {
        long l = System.currentTimeMillis();
        for (int i = 0; i < 1_00_000_000; i++) {
            BigDecimal.ONE.add(BigDecimal.TEN);
        }
        System.out.println("summation of BigDecimals "+(System.currentTimeMillis() - l));
    }


}
