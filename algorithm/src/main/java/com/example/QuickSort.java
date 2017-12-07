package com.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by haichen.cui on 25/07/2017.
 */

public class QuickSort {
    int[] a = new int[11];

    {
        Random r = new Random();
        for (int i = 0; i < a.length; i++) {
            a[i] = r.nextInt(100);
        }
    }

    public static void quickSort(ArrayList<Integer> items) {
        if (items.size() > 1) {
            ArrayList<Integer> smaller = new ArrayList<>();
            ArrayList<Integer> same = new ArrayList<>();
            ArrayList<Integer> larger = new ArrayList<>();

            Integer pivot = items.get(items.size() / 2);
            for (Integer item : items) {
                if (item < pivot) {
                    smaller.add(item);
                } else if (item > pivot) {
                    larger.add(item);
                } else {
                    same.add(item);
                }
            }

            quickSort(smaller);
            quickSort(larger);

            items.clear();
            items.addAll(smaller);
            items.addAll(same);
            items.addAll(larger);
        }
    }

    public static <T extends Comparable> void quickSort(List<T> list, int start, int end) {
        if (list == null || list.size() == 0 || list.size() == 1 || end - start <= 1)
            return;

        int indexOfChoosen = (end + start) / 2;
        int fromLeftToRightCount = 0;
        int sameCount = 0;
        T choosen = list.get(indexOfChoosen);
        T tmp;
        for (int i = start; i < end - fromLeftToRightCount; i++) {
            T t = list.get(i);
            int result = t.compareTo(choosen);
            if (result > 0 && i < indexOfChoosen) {
                fromLeftToRightCount++;
                tmp = list.remove(i--);
                list.add(end - 1, tmp);
                indexOfChoosen--;
            } else if (result < 0 && i > indexOfChoosen) {
                tmp = list.remove(i);
                list.add(indexOfChoosen , tmp);
                indexOfChoosen++;
            } else if (result == 0 && i < indexOfChoosen && i > indexOfChoosen + sameCount) {
                sameCount++;
                tmp = list.remove(i--);
                list.add(indexOfChoosen + 1, tmp);
                if (i < indexOfChoosen) {
                    indexOfChoosen--;
                }
            }
        }

        quickSort(list, start, indexOfChoosen);
        quickSort(list, indexOfChoosen + sameCount + 1, end);
    }
}
