import java.util.*;

class Solution {
    static String inputString;
    
    public int solution(String s) {
        if (s.length() == 1) return 1;

        inputString = s;
        int inputLength = s.length();
        int minLength = Integer.MAX_VALUE;

        // 전체 반복은 s 길이의 절반까지만
        for (int len = 1; len <= inputLength / 2; len++) {
            int length = 0;
            int idx = 0;

            // 자를 word가 s의 범위 안에 있는 경우까지 반복
            while(idx + len <= inputLength) {
                String word = getWord(idx, len);
                int count = 0;
                
                // 확인할 문자열이 s의 범위 안에 있고, 그 문자열이 word와 동일한 경우
                // 그 문자열의 개수를 구한다.
                while (idx + len <= inputLength && word.equals(getWord(idx, len))) {
                    idx += len;
                    count++;
                }
                // count가 1이면 문자열 길이만 더하고, 1보다 큰 경우에는 그 길이도 함께 더한다.
                length += word.length() + (count > 1 ? String.valueOf(count).length() : 0);
            }
            // 남은 문자열 붙이기
            length += inputLength - idx;
            minLength = Math.min(minLength, length);
        }
        
        return minLength;
    }
    
    private String getWord(int start, int len) {
        return inputString.substring(start, start + len);
    }
}