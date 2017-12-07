package com.example;

import java.util.Stack;

/**
 * Created by haichen.cui on 2017.09.21
 */

public class BracketMatch {
    static final char[] brackets = {'{', '}', '(', ')', '{', '}'};

    static Stack<Character> stack = new Stack<>();

    public static void main(String[] args) {
        System.out.println(bracketMatch(brackets));
    }

    static boolean bracketMatch(char[] brackets) {
        if (brackets == null) return false;

        for (int i = 0; i < brackets.length; i++) {
            char add = brackets[i];
            char top = '\u0000';

            if (!stack.empty()) {
                top = stack.peek();

                switch (top) {
                    case '{':
                        switch (add) {
                            case '}':
                                stack.pop();
                                continue;
                            case ']':
                                return false;
                            case ')':
                                return false;
                            default:
                                stack.push(add);
                        }
                        break;
                    case '[':
                        switch (add) {
                            case '}':
                                return false;
                            case ']':
                                stack.pop();
                                continue;
                            case ')':
                                return false;
                            default:
                                stack.push(add);
                        }
                        break;
                    case '(':
                        switch (add) {
                            case '}':
                                return false;
                            case ']':
                                return false;
                            case ')':
                                stack.pop();
                                continue;
                            default:
                                stack.push(add);
                        }
                        break;
                }
            } else {
                switch (add) {
                    case '}':
                    case ']':
                    case ')':
                        return false;
                    default:
                        stack.push(add);
                        break;
                }
            }
        }

        if (stack.empty()) {
            return true;
        } else {
            return false;
        }
    }
}
