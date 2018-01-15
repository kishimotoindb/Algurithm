package com.example;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Created by BigFaceBear on 2018.01.15
 */

public class PreorderTraversalNoRecursion {
    private static class Node {
        Node(int value, Node left, Node right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }

        int value;
        Node left;
        Node right;
    }

    static {
        Node rl = new Node(1, null, null);
        Node rr = new Node(2, null, null);
        root = new Node(0, rl, rr);

        Node rll = new Node(3, null, null);
        Node rlr = new Node(4, null, null);
        rl.left = rll;
        rl.right = rlr;

        Node rrl = new Node(5, null, null);
        Node rrr = new Node(6, null, null);
        rr.left = rrl;
        rr.right = rrr;

        Node rlll = new Node(7, null, null);
        Node rllr = new Node(8, null, null);
        rll.left = rlll;
        rll.right = rllr;

        Node rlrl = new Node(9, null, null);
        Node rlrr = new Node(10, null, null);
        rlr.left = rlrl;
        rlr.right = rlrr;

        Node rrll = new Node(11, null, null);
        Node rrlr = new Node(12, null, null);
        rrl.left = rrll;
        rrl.right = rrlr;

        Node rrrl = new Node(13, null, null);
        Node rrrr = new Node(14, null, null);
        rrr.left = rrrl;
        rrr.right = rrrr;
    }
    /*
     *                                                 0
     *                  1                                                             2
     *         3                 4                                      5                           6
     *      7     8          9      10                              11     12                    13   14
     */

    static Node root;

    public static void main(String[] args) {
        printIncursion(root);
        printNonIncursionWithQueue(root);
        printNonIncursionWithStack(root);
    }

    static void printIncursion(Node node) {
        if (node == null) return;

        System.out.println(node.value);

        Node left = node.left;
        if (left != null) {
            printIncursion(left);
        }

        Node right = node.right;
        if (right != null) {
            printIncursion(right);
        }
    }

    static void printNonIncursionWithQueue(Node node) {
        Queue<Node> queue = new LinkedList<>();
        queue.offer(node);
        while (!queue.isEmpty()) {
            Node pollNode = queue.poll();
            System.out.println(pollNode.value);

            if (pollNode.left != null) {
                queue.offer(pollNode.left);
            }
            if (pollNode.right != null) {
                queue.offer(pollNode.right);
            }
        }
    }

    static void printNonIncursionWithStack(Node node) {
        Stack<Node> stack = new Stack<>();
        stack.push(node);
        while (!stack.isEmpty()) {
            Node pollNode = stack.pop();
            System.out.println(pollNode.value);

            if (pollNode.right != null) {
                stack.push(pollNode.right);
            }
            if (pollNode.left != null) {
                stack.push(pollNode.left);
            }
        }
    }


}
