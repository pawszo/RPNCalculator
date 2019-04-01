package sample.utils;

import java.util.Scanner;
import java.util.Stack;

public class RPNutils {

    private static boolean isNumeric(String string) {
        try {
            Double.valueOf(string);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }


    public static String convertToRPN(String classicEquation) {
        String rpn = "";
        Stack<String> stack = new Stack<>();
        Scanner sc = new Scanner(classicEquation);
        String currentElement;
        while (sc.hasNext()) {
            currentElement = sc.next();
            System.out.println(currentElement);
            if (isNumeric(currentElement)) {
                rpn += currentElement;

            } else if (currentElement.equals("(")) {
                stack.push(currentElement);

            } else if (currentElement.equals(")")) {
                //stack.pop();
                while (!stack.peek().equals("(")) {
                    rpn += stack.pop();
                }
                stack.pop();

            } else {
                if (currentElement.equals("^")) {
                    stack.push(currentElement);
                } else if (currentElement.equals("*")) {
                    if (stack.size() == 0 || stack.peek().equals("+") || stack.peek().equals("-")) {
                        stack.push(currentElement);
                    } else {
                        while (stack.peek().equals("*") || stack.peek().equals("/") || stack.peek().equals("%") || stack.peek().equals("^")) {
                            rpn += stack.pop();
                        }
                    }
                }
                else if (currentElement.equals("/")) {
                    if (stack.size() == 0 || stack.peek().equals("+") || stack.peek().equals("-")) {
                        stack.push(currentElement);
                    } else {
                        while (stack.peek().equals("*") || stack.peek().equals("/") || stack.peek().equals("%") || stack.peek().equals("^")) {
                            rpn += stack.pop();
                        }
                    }
                }
                else if (currentElement.equals("%")) {
                    if (stack.size() == 0 || stack.peek().equals("+") || stack.peek().equals("-")) {
                        stack.push(currentElement);
                    } else {
                        while (stack.peek().equals("*") || stack.peek().equals("/") || stack.peek().equals("%") || stack.peek().equals("^")) {
                            rpn += stack.pop();
                        }
                    }
                }
                else if (currentElement.equals("-")) {
                    if (stack.size() == 0) {
                        stack.push(currentElement);
                    } else {
                        while (stack.peek().equals("*") || stack.peek().equals("/") || stack.peek().equals("%") || stack.peek().equals("^") || stack.peek().equals("-") || stack.peek().equals("+")) {
                            rpn += stack.pop();
                        }
                    }
                }
                else if (currentElement.equals("+")) {
                    if (stack.size() == 0) {
                        stack.push(currentElement);
                    } else {
                        while (stack.peek().equals("*") || stack.peek().equals("/") || stack.peek().equals("%") || stack.peek().equals("^") || stack.peek().equals("-") || stack.peek().equals("+")) {
                            rpn += stack.pop();
                        }
                    }
                }
            }
        }
        while(stack.size() > 0) {
            rpn += stack.pop();
        }
        return rpn;
    }

}
