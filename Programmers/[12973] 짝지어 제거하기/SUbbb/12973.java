import java.util.*;

class Solution {
    public int solution(String s) {
        Stack<Character> stack = new Stack<>();
        int len = s.length();
        
        for (int i = 0; i < len; i++) {
            char ch = s.charAt(i);
            char stackCh = ' ';
            
            if (!stack.isEmpty()) stackCh = stack.peek();
            
            if (ch == stackCh) stack.pop();
            else stack.push(ch);
        }
        
        return stack.isEmpty() ? 1 : 0;
    }
}