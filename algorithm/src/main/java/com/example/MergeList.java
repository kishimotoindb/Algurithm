package com.example;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;

/**
 * Created by haichen.cui on 2017.09.20
 */

public class MergeList {
    static int[] arrayA = {1, 10, 30, 40, 60, 80};
    static int[] arrayB = {5, 15, 35, 45};

    static final int sizeCD = 10000;
    static ArrayList<Integer> listC = new ArrayList<>(sizeCD);
    static ArrayList<Integer> listD = new ArrayList<>(sizeCD);

    static int[] arrayE = {1, 10, 30, 40, 60, 80};
    static int[] arrayF = {5, 10, 30, 0};

    static {
        Random random = new Random();
        for (int i = 0; i < sizeCD; i++) {
            listC.add(random.nextInt(sizeCD));
            listD.add(random.nextInt(sizeCD));
        }
    }

    public static void main(String[] args) {
//        for (int i : mergeArray(arrayA, arrayB)) {
//            System.out.println(i);
//        }

        for (int i : unionArrayNoSort(arrayE, arrayF)) {
            System.out.println(i);
        }
    }

    /*
     * 无排序，直接求并集 A = A U B
     * 时间复杂度 len_arrayA * len_arrayB
     */
    static int[] unionArrayNoSort(int[] arrayA, int[] arrayB) {
        if (arrayA == null || arrayB == null) {
            return null;
        }

        int[] result = new int[arrayA.length + arrayB.length];
        for (int i = 0; i < arrayA.length; i++) {
            result[i] = arrayA[i];
        }

        int index = arrayA.length;
        for (int i = 0; i < arrayB.length; i++) {
            int d = arrayB[i];

            boolean isExist = false;
            for (int j = 0; j < arrayA.length; j++) {
                if (arrayA[j] == d) {
                    isExist = true;
                    break;
                }
            }
            if (!isExist) {
                result[index++] = d;
            }
        }
        return result;
    }

    /*
     * 无排序，直接求并集 A = A U B
     * 时间复杂度:
     * ListA.size = la
     * ListB.size = lb
     * 结果：la * lb
     */
    static void unionArrayNoSort(ArrayList<Integer> listA, ArrayList<Integer> listB) {
        if (listA == null || listB == null) {
            return;
        }

        for (int i = 0; i < listB.size(); i++) {
            Integer d = listB.get(i);

            boolean isExist = false;
            for (int j = 0; j < listA.size(); j++) {
                if (listA.get(i) == d) {
                    isExist = true;
                    break;
                }
            }
            if (!isExist) {
                listA.add(d);
            }
        }
    }

    /*
     * 先排序，再求并集 A = A U B(A和B，同一个集合中的元素不同)
     * 时间复杂度:
     * ListA.size = la
     * ListB.size = lb
     * 结果：
     * = la*log(la) + lb*log(lb) + la + lb
     * = la*(log(la) + 1) + lb*(log(lb) + 1)
     */
    static ArrayList<Integer> unionArrayWithSort(ArrayList<Integer> listA, ArrayList<Integer> listB) {
        if (listA == null || listB == null) {
            return null;
        }
        QuickSort.quickSort(listA);
        QuickSort.quickSort(listB);

        ArrayList<Integer> listC = new ArrayList<>();
        int i, j;
        i = j = 0;

        while (i < listA.size() && j < listB.size()) {
            Integer a = listA.get(i);
            Integer b = listB.get(j);

            if (a < b) {
                listC.add(a);
                i++;
            } else if (a > b) {
                listC.add(b);
                j++;
            } else {
                listC.add(a);
                i++;
                j++;
            }
        }

        while (i < listA.size()) {
            listC.add(listA.get(i++));
        }

        while (i < listB.size()) {
            listC.add(listB.get(i++));
        }

        return listC;
    }

    /*
     * 两个有序表合并(listA和listB传进来的时候已经排过序)
     * 时间复杂度：arrayA和arrayB的表长之和 lenA+lenB
     *
     * 如果是两个无序表合并，那应该是直接合并，然后排序。时间复杂度是排序算法的时间复杂度+较短表的表长
     * (lenA+lenB)log((lenA+lenB))+min(lenA,lenB)
     */
    static ArrayList<Integer> mergeList(ArrayList<Integer> listA, ArrayList<Integer> listB) {
        if (listA == null || listB == null) {
            return null;
        }

        ArrayList<Integer> listC = new ArrayList<>();
        int i, j;
        i = j = 0;

        while (i < listA.size() && j < listB.size()) {
            int a = listA.get(i);
            int b = listB.get(j);
            if (a < b) {
                listC.add(a);
                i++;
            } else if (a > b) {
                listC.add(b);
                j++;
            } else {
                listC.add(a);
                listC.add(b);
                i++;
                j++;
            }
        }

        while (i < listA.size()) {
            listC.add(listA.get(i++));
        }

        while (i < listB.size()) {
            listC.add(listB.get(i++));
        }

        return listC;
    }


    /*
     * 两个有序表合并
     * 时间复杂度：arrayA和arrayB的表长之和 lenA+lenB
     *
     * 如果是两个无序表合并，那应该是直接合并，然后排序。时间复杂度是排序算法的时间复杂度+较短表的表长
     * (lenA+lenB)log((lenA+lenB))+min(lenA,lenB)
     */
    static int[] mergeArray(int[] arrayA, int[] arrayB) {
        if (arrayA == null || arrayB == null) {
            return null;
        }

        int[] arrayC = new int[arrayA.length + arrayB.length];
        int i, j, k;
        i = j = k = 0;

        while (i < arrayA.length && j < arrayB.length) {
            int a = arrayA[i];
            int b = arrayB[j];
            if (a < b) {
                arrayC[k++] = a;
                i++;
            } else if (a > b) {
                arrayC[k++] = b;
                j++;
            } else {
                arrayC[k++] = a;
                arrayC[k++] = b;
                i++;
                j++;
            }
        }

        while (i < arrayA.length) {
            arrayC[k++] = arrayA[i++];
        }

        while (i < arrayB.length) {
            arrayC[k++] = arrayB[i++];
        }

        return arrayC;
    }

    static LinkedList<Integer> mergeLinkedList(LinkedList<Integer> listA, LinkedList<Integer> listB) {

        return new LinkedList<>();
    }
}
