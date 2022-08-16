import java.util.*;

class Solution {
    private String str;
    private Stack<Character> stack;
    
    public int solution(String s) {
        this.str = s;
        int len = s.length();
        
        int answer = 0;
        
        if (len % 2 == 1) return answer;
        
        for (int i = 0; i < len; i++) {
            stack = new Stack<>();
            // i부터 문자열 길이까지가 올바르다면, 0부터 i까지 올바른지 확인
            if (isValid(i, len))
                answer += isValid(0, i) ? 1 : 0;
        }
        
        return answer;
    }
    
    // 주어진 인덱스 사이의 문자열이 올바른 문자열인지 판단
    private boolean isValid(int s, int e) {
        if (s == e) return true;
        
        for (int i = s; i < e; i++) {
            char ch = str.charAt(i);
            
            if (stack.isEmpty()) {
                if (isCloseCh(ch)) return false;
                stack.add(ch);
            } else {
                char stCh = stack.peek();

                if (isPair(stCh, ch)) stack.pop();
                else if (isCloseCh(ch)) return false;
                else stack.add(ch);
            }
        }
        
        return true;
    }
    
    private boolean isCloseCh(char ch) {
        return ch == '}' || ch == ']' || ch == ')';
    }
    
    private boolean isPair(char stCh, char ch) {
        return (stCh == '(' && ch == ')') || (stCh == '{' && ch == '}') || (stCh == '[' && ch == ']');
    }
}