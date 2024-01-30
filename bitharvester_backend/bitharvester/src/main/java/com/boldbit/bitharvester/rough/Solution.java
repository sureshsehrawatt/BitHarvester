package com.boldbit.bitharvester.rough;

import java.util.Stack;

class Solution {
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < tokens.length; i++) {
            String token = tokens[i];
            if (!(token == "+" || token == "-" || token == "*" || token == "/")) {
                stack.push(Integer.parseInt(token));
            } else {
                int b = stack.pop();
                int a = stack.pop();
                switch (token) {
                    case "+":
                        stack.push(a + b);
                        break;
                    case "-":
                        stack.push(a - b);
                        break;
                    case "*":
                        stack.push(a * b);
                        break;
                    case "/":
                        if (b != 0) {
                            stack.push(a / b);
                            break;
                        } else {
                            throw new ArithmeticException("Division by zero");
                        }
                    default:
                        throw new IllegalArgumentException("Invalid operator: " + token);
                }
            }
        }
        return stack.pop();
    }

    public static void main(String[] args) {
        Solution obj=new Solution();
        String[] tokens = {"10","6","9","3","+","-11","*","/","*","17","+","5","+"};
        obj.evalRPN(tokens);
    }
}