import java.util.*;

class Solution {
    String[] table = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
    public int solution(String s) {
        StringBuilder answer = new StringBuilder();
        Map<String, Integer> map = new HashMap<>();

        int idx = 0;
        for (String t : table) map.put(t, idx++);

        idx = 0;
        while(idx < s.length()) {
            char ch = s.charAt(idx);
            if (Character.isDigit(ch)) {
                answer.append(ch);
                idx++;
            } else {
                for (String key : map.keySet()) {
                    if (s.contains(key)) {
                        int start = s.indexOf(key);
                        if (start != idx) continue;
                        s = s.substring(0, start) + s.substring(start + key.length());
                        answer.append(map.get(key));
                        break;
                    }
                }
            }
        }

        return Integer.parseInt(answer.toString());
    }
}