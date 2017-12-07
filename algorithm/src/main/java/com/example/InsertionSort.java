package com.example;

import java.util.Random;

/**
 * Created by haichen.cui on 25/07/2017.
 */

public class InsertionSort {
    int[] a = new int[11];

    {
        Random r = new Random();
        for (int i = 0; i < a.length; i++) {
            a[i] = r.nextInt(100);
        }
    }

    public void insertionSort() {
        int j;
        for (int i = 1; i < a.length; i++) {
            int tmp = a[i];
            for ( j = i; j > 0 && tmp < a[j - 1]; j--) {
                a[j] = a[j - 1];
                a[j - 1] = tmp;
            }
        }
        for (int i : a) {
            System.out.println(i);
        }
    }
}
