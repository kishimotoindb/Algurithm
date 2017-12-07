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

        for (int i = 2; i < 40; i++) {
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
        printList(headA);
        printList(headB);

        Node head = mergeLinkedList(headA, headB);

        printList(head);
    }

    //往B里插入A
    private static Node mergeLinkedList(Node headA, Node headB) {
        if (headA == null && headB == null) return null;
        else if (headA == null) {
            return headB;
        } else if (headB == null) {
            return headA;
        }

        Node spa = headA;
        Node spb = headB;   //两个链表的元素比较时，比较大小的情况发生反转时的起始节点
        Node cpa = headA;   //指向链表A当前循环位置的指针
        Node cpb = headB;   //指向链表B当前循环位置的指针
        Node head = headA.value <= headB.value ? headA : headB;

        while (true) {

            if (cpa.value <= cpb.value) {
                if (cpa.next == null) {
                    cpa.next = cpb;
                    break;
                } else {
                    if (cpa.next.value <= cpb.value) {
                        cpa = cpa.next;
                    } else {
                        spa = cpa.next;
                        cpa.next = cpb;
                        cpa = spa;
                    }
                }
            } else {
                //cpa.value>cpb.value

                if (cpa.next == null && cpb.next != null) {
                    while (true) {
                        if (cpb.next.value <= cpa.value) {
                            cpb = cpb.next;

                            if (cpb.next == null) {
                                cpb.next = spa;
                                break;
                            }
                        } else {
                            spb = cpb.next;
                            cpb.next = spa;
                            cpa.next = spb;
                            break;
                        }
                    }
                    break;
                } else if (cpb.next == null) {
                    cpb.next = cpa;
                    break;
                } else {
                    if (cpa.next.value <= cpb.next.value) {
                        cpa = cpa.next;
                    } else {
                        spb = cpb.next;
                        cpb.next = spa;
                        spa = cpa.next;
                        cpa.next = cpb.next;
                        cpb = spb;
                        cpa = spa;
                    }
                }
            }
        }
        return head;
    }

    private static void printList(Node head) {
        if (head == null) {
            System.out.println("列表为空");
            return;
        }

        Node node = head;
        while (node != null) {
            System.out.print(node.value + " ");
            node = node.next;
        }
        System.out.println();
    }
}
