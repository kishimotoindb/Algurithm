package com.example;

/**
 * Created by haichen.cui on 2017.09.13
 * 多项式计算：
 * 1+sum(1,100)(x^i)/i
 */

public class Polynomial {
    public static void main(String[] args) {
        final int number = 10000;
        long start1 = System.currentTimeMillis();
        for (int i = 0; i < number; i++) {
            f1();
        }
        long end1 = System.currentTimeMillis();
        System.out.println("time duration is " + (end1 - start1) / (double) number + "ms");

        long start2 = System.currentTimeMillis();
        for (int i = 0; i < number; i++) {
            f2();
        }
        long end2 = System.currentTimeMillis();
        System.out.println("time duration is " + (end2 - start2) / (double) number + "ms");
    }

    static void f1() {
        double result = 1;
        for (int i = 1; i <= 100; i++) {
            result += Math.pow(1.1, i) / i;
        }
//        System.out.println("result1 is " + result);
    }

    static void f2() {
        double p = 0.01;
        final double x = 1.1;
        for (int i = 100; i > 1; i--) {
            double a1 = 1.0 / ((double) (i - 1));
            p = a1 + x * p;
        }
        p = 1 + 1.1 * p;
//        System.out.println("result2 is " + p);
    }
}
