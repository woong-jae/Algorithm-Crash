import java.util.Stack;

class Solution {
    public int solution(String s) {
        int n = s.length();
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < n; i++) {
            if (!stack.isEmpty() && stack.peek() == s.charAt(i)) {
                stack.pop();
                continue;
            }
            stack.add(s.charAt(i));
        }

        return stack.isEmpty() ? 1 : 0;
    }
}