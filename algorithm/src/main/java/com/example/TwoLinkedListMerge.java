package com.example;

import java.util.Random;

/**
 * Created by BigFaceBear on 2017.12.06
 * <p>
 * <p>
 * 两个有序单向链表做合并排序
 */

public class TwoLinkedListMerge {
    static Node headA = new Node(1);
    static Node headB = new Node(0);
    static Random r = new Random();

    static class Node {
        public Node(int value) {
            this.value = value;
        }

        int value;
        Node next;
    }


    static {
        Node a = headA;
        Node b = headB;

        for (int i = 2; i < 100; i++) {
            if (r.nextBoolean()) {
                a.next = new Node(i);
                a = a.next;
            } else {
                b.next = new Node(i);
                b = b.next;
            }
        }
    }


    public static void main(String[] args) {
        //printList(headA);
        //printList(headB);

//        Node head1 = mergeLinkedList1(headA, headB);
        Node head2 = mergeLinkedList2(headA, headB);

//      printList(head1);
        printList(head2);
    }

    //往B里插入A(有问题，95行的while有时会死循环)，这个写法太复杂了，逻辑不清晰。正确的见下面的方法mergeLinkedList2
    private static Node mergeLinkedList1(Node headA, Node headB) {
        if (headA == null && headB == null) return null;
        else if (headA == null) {
            return headB;
        } else if (headB == null) {
            return headA;
        }

        Node spa;
        Node cpa = headA;   //指向链表A当前循环位置的指针
        Node cpb = headB;   //指向链表B当前循环位置的指针
        Node head = headA.value <= headB.value ? headA : headB;

        while (cpa != null) {
            System.out.println("while cpa!=null");

            if (cpa.value <= cpb.value) {
                while (cpa.next != null && cpa.next.value <= cpb.value) {
                    cpa = cpa.next;
                    System.out.println("while cpa.next != null && cpa.next.value <= cpb.value");
                }

                //1.cap.next==null 2.cpa.next.value>cpb.value
                if (cpa.next == null) {
                    //如果cpa的元素均小于cpb，那么把cpa放到cpb的前头即可
                    cpa.next = cpb;
                    break;
                } else {
                    Node tmp = cpa.next;
                    cpa.next = cpb;
                    cpa = tmp;
                }

            } else {

                if (cpa.value > cpb.next.value) {
                    //cpa>cpb && cpa>cpb.next

                    //主要进行B链表指针的挪动
                    while (cpb.next != null && cpa.value > cpb.next.value) {
                        cpb = cpb.next;
                        System.out.println("while (cpb.next != null && cpa.value > cpb.next.value) ");
                        System.out.println("cpa.value is " + cpa.value);
                        System.out.println("cpb.value is " + cpb.value);
                    }

                    if (cpb.next == null) {
                        cpb.next = cpa;
                        break;
                    }

                } else {
                    //cpa>cpb && cpa<cpb.next

                    //记录第一个比当前cpb大的cpa
                    spa = cpa;

                    while (cpa.next != null && cpa.next.value <= cpb.next.value) {
                        cpa = cpa.next;
                        System.out.println(" while (cpa.next != null && cpa.next.value <= cpb.next.value)");
                    }

                    if (cpa.next == null) {
                        Node tmp = cpb.next;
                        cpb.next = spa;
                        cpa.next = tmp;

                    } else {

                        Node tmpA = cpa.next;
                        Node tmpB = cpb.next;
                        cpb.next = spa;
                        cpa.next = tmpB;
                        cpa = tmpA;
                    }
                }
            }
        }
        return head;
    }

    //往B里插入A
    private static Node mergeLinkedList2(Node headA, Node headB) {
        if (headA == null && headB == null) return null;
        else if (headA == null) {
            return headB;
        } else if (headB == null) {
            return headA;
        }

        Node prevBPointer = headB;   //指向链表B当前循环位置上一个位置的指针
        Node curBPointer = headB;    //指向链表B当前循环位置的指针
        Node prevAPointer = headA;   //指向链表A当前循环位置上一个位置的指针
        Node curAPointer = headA;    //指向链表A当前循环位置的指针
        Node headerOfResult = headA.value < headB.value ? headA : headB;

        while (curBPointer != null) {

            if (curAPointer.value <= curBPointer.value) {

                while (curAPointer != null && curAPointer.value <= curBPointer.value) {
                    prevAPointer = curAPointer;
                    curAPointer = curAPointer.next;
                }

                prevAPointer.next = curBPointer;

                if (curAPointer == null) {
                    break;
                }

            } else {

                while (curBPointer != null && curAPointer.value > curBPointer.value) {
                    prevBPointer = curBPointer;
                    curBPointer = curBPointer.next;
                }

                prevBPointer.next = curAPointer;

            }
        }
        return headerOfResult;
    }

    private static void printList(Node head) {
        if (head == null) {
            System.out.println("列表为空");
            return;
        }

        Node node = head;
        int i = 0;
        while (node != null) {
            System.out.print(node.value + " ");
            node = node.next;
            if (++i % 10 == 0) {
                System.out.println();
            }
        }
    }
}
