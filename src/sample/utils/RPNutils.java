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
        int counter = 1;
        while (sc.hasNext()) {
            currentElement = sc.next();
            if (isNumeric(currentElement)) {
                rpn = rpn + currentElement + " ";
                //      System.out.println("Counter = " + counter++);

            } else if (currentElement.equals("(")) {
                stack.push(currentElement);

            } else if (currentElement.equals(")")) {
                //stack.pop();
                while (!stack.peek().equals("(")) {
                    rpn = rpn + stack.pop() + " ";
                    if (stack.empty()) break;
                }
                stack.pop();

            } else {
                if (currentElement.equals("^")) {
                    stack.push(currentElement);
                } else if (currentElement.equals("*")) {
                    if (stack.empty() || stack.peek().equals("+") || stack.peek().equals("-")) {
                        stack.push(currentElement);
                    } else {
                        while (stack.peek().equals("*") || stack.peek().equals("/") || stack.peek().equals("%") || stack.peek().equals("^")) {
                            rpn = rpn + stack.pop() + " ";
                            if (stack.empty()) break;
                        }
                        stack.push(currentElement);
                    }
                } else if (currentElement.equals("/")) {
                    if (stack.empty() || stack.peek().equals("+") || stack.peek().equals("-")) {
                        stack.push(currentElement);
                    } else {
                        while (stack.peek().equals("*") || stack.peek().equals("/") || stack.peek().equals("%") || stack.peek().equals("^")) {
                            rpn = rpn + stack.pop() + " ";
                            if (stack.empty()) break;
                        }
                        stack.push(currentElement);
                    }
                } else if (currentElement.equals("%")) {
                    if (stack.empty() || stack.peek().equals("+") || stack.peek().equals("-")) {
                        stack.push(currentElement);
                    } else {
                        while (stack.peek().equals("*") || stack.peek().equals("/") || stack.peek().equals("%") || stack.peek().equals("^")) {
                            rpn = rpn + stack.pop() + " ";
                            if (stack.empty()) break;
                        }
                        stack.push(currentElement);
                    }
                } else if (currentElement.equals("-")) {
                    if (stack.empty()) {
                        stack.push(currentElement);
                    } else {
                        while (stack.peek().equals("*") || stack.peek().equals("/") || stack.peek().equals("%") || stack.peek().equals("^") || stack.peek().equals("-") || stack.peek().equals("+")) {
                            rpn = rpn + stack.pop() + " ";
                            if (stack.empty()) break;
                        }
                        stack.push(currentElement);
                    }
                } else if (currentElement.equals("+")) {
                    if (stack.empty()) {
                        stack.push(currentElement);
                        System.out.println("push pierwszy plus");
                    } else {
                        while (stack.peek().equals("*") || stack.peek().equals("/") || stack.peek().equals("%") || stack.peek().equals("^") || stack.peek().equals("-") || stack.peek().equals("+")) {
                            rpn = rpn + stack.pop() + " ";
                            System.out.println("POP");
                            if (stack.empty()) break;
                        }
                        System.out.println("push drugi plus");
                        stack.push(currentElement);
                    }
                }
            }
        }
        while (stack.size() > 0) {
            rpn = rpn + stack.pop() + " ";
        }
        return rpn;
    }

    public static double evaluate(String rpnEquation) {
        Stack<Double> stack = new Stack<>();
//        stack.push(5d); // wepchnij
//        stack.pop(); // wyciągnij element ostatnio dodany
//        stack.peek(); // podejrzyj element ostatnio dodany
        Scanner sc = new Scanner(rpnEquation);
        String currentElement;
        double a, b, result;
        while (sc.hasNext()) {
            currentElement = sc.next();
            System.out.println("evaluate: sc.next() == " + currentElement);
            if (isNumeric(currentElement)) {
                stack.push(Double.valueOf(currentElement));
            } else {
                b = stack.pop();
                a = stack.pop();
                result = evaluateOperation(currentElement, a, b);
                stack.push(result);
            }
        }

        return stack.pop();
    }

    private static double evaluateOperation(String operation, double a, double b) {
        switch (operation) {
            case "+":
                return a + b;
            case "-":
                return a - b;
            case "*":
                return a * b;
            case "/":
                return a / b;
            case "^":
                return Math.pow(a, b);
            case "%":
                return 100 * a / b;
            default:
                throw new UnsupportedOperationException();
        }
    }


}
