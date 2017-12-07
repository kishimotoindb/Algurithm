package com.example;

import java.util.ArrayList;
import java.util.Random;

public class MyClass {
    static ArrayList<Integer> items = new ArrayList<>();

    static {
        Random r = new Random();
        for (int i = 0; i < 500; i++) {
            items.add(r.nextInt(100));
        }
    }


    public static void main(String[] args) {
//        new InsertionSort().insertionSort();
//        QuickSort.quickSort(items);
        QuickSort.quickSort(items, 0, items.size());
        for (Integer item : items) {
            System.out.print(item+" ");
        }
    }
}
