package com.example;

import java.util.Random;

/**
 * Created by haichen.cui on 24/07/2017.
 */

public class LinearSearch {
    int[] a = new int[101];

    {
        Random r = new Random(System.currentTimeMillis());
        for (int i = 0; i < a.length; i++) {
            a[i] = r.nextInt();
        }
    }

    int[][] ta;

    public void search() {
        int row = a.length / 5;
        if (a.length % 5 != 0) row++;
        ta = new int[row][5];

        row = 0;
        for (int i = 0; i < a.length; i++) {
            ta[row][i % 5] = a[i];
            if (i % 5 == 4) row++;
        }

        for (int i = 0; i < ta.length; i++) {
            int[] tmp = ta[i].clone();
            for (int j = 0; j < tmp.length; j++) {
                for (int k = 1; k < tmp.length; k++) {
                    if (tmp[k] < tmp[j]) {
                        int mid = tmp[k];
                        tmp[k] = tmp[j];
                        tmp[j] = mid;
                    }
                }
            }
        }
    }
}
