import java.util.Stack;

public class infixToPostfix {
    public static void main(String[] args) {
        System.out.println(toPostfix("5*2+3/(4/2+1)"));
        String[] tokens = {"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"};
        System.out.println(evalRPN(tokens));
    }

    /*
    1. number, directly output
    2. operators, pop out higher precedence operators
    3. '(', push in while ')' pop out all the operators till '('
     */
    public static String toPostfix(String infix) {
        Stack<Character> operatorStack = new Stack<>();
        StringBuilder stringBuilder = new StringBuilder();
        char[] lists = infix.toCharArray();
        for (int i = 0; i < lists.length; i++) {
            if ((byte) lists[i] >= 48 && (byte) lists[i] <= 48 + 9) { // directly output
                stringBuilder.append(lists[i]);
                while (i < lists.length - 1 && (byte) lists[i + 1] >= 48 && (byte) lists[i + 1] <= 48 + 9) {
                    stringBuilder.append(lists[i + 1]); // not a single num
                    i++;
                }
            } else if (lists[i] == '(') { // directly push
                operatorStack.push(lists[i]);
            } else if (lists[i] == ')') { // pop till '('
                while (operatorStack.peek() != '(') {
                    stringBuilder.append(operatorStack.pop());
                }
                operatorStack.pop();
            } else if (lists[i] == '+' || lists[i] == '-') {
                while (!operatorStack.isEmpty() && (operatorStack.peek() == '*' || operatorStack.peek() == '/')) {
                    stringBuilder.append(operatorStack.pop());
                }
                operatorStack.push(lists[i]);
            } else if (lists[i] == '*' || lists[i] == '/') {
                operatorStack.push(lists[i]);
            }
        }
        while (!operatorStack.isEmpty()) {
            stringBuilder.append(operatorStack.pop());
        }
        return stringBuilder.toString();
    }

    // leetCode: https://leetcode-cn.com/problems/evaluate-reverse-polish-notation/
    public static int evalRPN(String[] tokens) {
        Stack<Integer> integers = new Stack<>();
        for (String token : tokens) {
            switch (token) {
                case "*": {
                    int num2 = integers.pop();
                    int num1 = integers.pop();
                    integers.push(num1 * num2);
                    break;
                }
                case "/": {
                    int num2 = integers.pop();
                    int num1 = integers.pop();
                    integers.push(num1 / num2);
                    break;
                }
                case "+": {
                    int num2 = integers.pop();
                    int num1 = integers.pop();
                    integers.push(num1 + num2);
                    break;
                }
                case "-": {
                    int num2 = integers.pop();
                    int num1 = integers.pop();
                    integers.push(num1 - num2);
                    break;
                }
                default:
                    integers.push(Integer.parseInt(token));
                    break;
            }
        }
        return integers.pop();
    }
}
