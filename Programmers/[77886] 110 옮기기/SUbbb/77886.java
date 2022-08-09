import java.util.*;

class Solution {
    public String[] solution(String[] s) {
        int len = s.length;
        
        String[] answer = new String[len];
        
        for (int i = 0; i < len; i++)
            answer[i] = findFirst(s[i]);
        
        return answer;
    }
    
    private String findFirst(String s) {
        String ooz = "110";
        
        Stack<Character> stack = new Stack<>();
        String str = s;
        int strCount = 0;

        // 모든 110 개수 카운트
        for(int i = 0; i < str.length(); i++) {
            stack.push(str.charAt(i));

            if (stack.size() < 3) continue;
            
            char first = stack.pop();
            if(first != '0') {
                stack.push(first);
                continue;
            }
            char second = stack.pop();
            if(second != '1') {
                stack.push(second);
                stack.push(first);
                continue;
            }
            char third = stack.pop();
            if(third != '1') {
                stack.push(third);
                stack.push(second);
                stack.push(first);
                continue;
            }
            strCount++;
        }
        
        StringBuilder sb = new StringBuilder();
        int index = -1, size = stack.size() - 1;
        
        // 스택 값을 sb 맨 앞에 추가
        // 0을 만나면 index 증가
        while(!stack.isEmpty()) {
            char value = stack.pop();
            sb.insert(0, value);
            
            if(index == -1 && value == '0')
                index = size;
            size--;
        }
        
        // 남은 strCount만큼 추가
        // index == -1이라면, 남은 문자열에 0이 없다는 의미로, 맨 앞에 110을 붙여야 함
        // 그렇지 않다면, index + 1부터 110을 붙여야 함
        int offset = index == -1 ? 0 : ++index;
        while(strCount-- > 0)
            sb.insert(offset, ooz);
        
        return sb.toString();
    }
}