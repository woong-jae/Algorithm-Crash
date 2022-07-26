import java.util.*;

class Solution {
    public String solution(String p) {
        // 이미 올바른 경우 반환
        if (isProper(p)) return p;
        
        // 올바르지 않은 경우
        return recursive(p);
    }
    
    private String recursive(String p) {
        // 빈 문자열인 경우 반환
        if (Objects.equals(p, "")) return p;
        
        // u와 v로 분리
        String[] uv = splitString(p);
        String u = uv[0];
        String v = uv[1];
        
        // u가 올바르다면, v에 대해 recurvie()한 결과를 붙여 반환
        if (isProper(u)) return u + recursive(v);
        
        // u가 올바르지 않은 경우
        StringBuilder sb = new StringBuilder("(");
        sb.append(recursive(v)).append(")").append(reverseBracket(u.substring(1, u.length() - 1)));
        return sb.toString();
    }
    
    // 올바른 괄호 문자열인지 확인하는 함수, Stack 사용
    private boolean isProper(String p) {
        Stack<Character> stack = new Stack<>();
        int idx = 0;
        
        while (idx < p.length()) {
            char ch = p.charAt(idx);
            if (ch == '(')
                stack.push(ch);
            else
                if (stack.size() > 0)
                    stack.pop(); 
            
            idx++;
        }
        
        return stack.isEmpty();
    }
    
    // 분리하는 함수, (의 개수와 )의 개수가 같은 경우까지가 u, 나머지는 v
    private String[] splitString(String p) {
        String u = "";
        String v = "";
        int lcount = 0;
        int rcount = 0;
        int idx = 0;
        
        while(idx < p.length()) {
            if (p.charAt(idx) == '(') lcount++;
            else rcount++;
            idx++;
            
            if (lcount == rcount) {
                u = p.substring(0, idx);
                v = p.substring(idx);
                break;
            }
        }
        
        return new String[] {u, v};
    }
    
    // 주어진 문자열의 첫번째와 마지막 문자를 제거하고, 나머지 문자를 뒤집는 함수
    private String reverseBracket(String str) {
        StringBuilder sb = new StringBuilder();
        
        for (int i = 0; i < str.length(); i++)
            sb.append(str.charAt(i) == '(' ? ")" : "(");
        
        return sb.toString();
    }
}