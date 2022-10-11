import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String expression = br.readLine(); br.close();
        StringBuilder sb = new StringBuilder();
        Stack<Character> stack = new Stack<>();
        Map<Character, Integer> priority = initMap();

        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            switch (c) {
                case '+':
                case '-':
                case '*':
                case '/':
                    while (!stack.isEmpty() && priority.get(stack.peek()) >= priority.get(c))
                        sb.append(stack.pop());
                    stack.add(c);
                    break;
                case '(':
                    stack.add(c);
                    break;
                case ')':
                    while(!stack.isEmpty() && stack.peek() != '(')
                        sb.append(stack.pop());
                    stack.pop();
                    break;
                default:
                    sb.append(c);
            }
        }

        while (!stack.isEmpty())
            sb.append(stack.pop());

        System.out.println(sb);
    }

    private static Map<Character, Integer> initMap() {
        Map<Character, Integer> operatorMap = new HashMap<>();
        operatorMap.put('+', 1);
        operatorMap.put('-', 1);
        operatorMap.put('*', 2);
        operatorMap.put('/', 2);
        operatorMap.put('(', 0);
        operatorMap.put(')', 0);
        return operatorMap;
    }
}