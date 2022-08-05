import java.util.Arrays;

class Solution {
    private int count;
    public String[] solution(String[] s) {
        int n = s.length;
        String[] answer = new String[n];

        for (int i = 0; i < n; i++) {
            count = 0;
            String afterRemoved = remove(s[i]);
            answer[i] = make(afterRemoved, count);
        }
        return answer;
    }

    private String remove(String s) {
        int len = s.length();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < len; i++) {
            sb.append(s.charAt(i));
            if (sb.length() >= 3)
                if (sb.subSequence(sb.length() - 3, sb.length()).equals("110")) {
                    sb.delete(sb.length() - 3, sb.length());
                    count++;
                }
        }
        return sb.toString();
    }

    private String make(String s, int count) {
        int len = s.length();
        int index = len - 1;
        StringBuilder result = new StringBuilder(s);
        StringBuilder sb = new StringBuilder();
        sb.append("110".repeat(count));

        for (; index >= 0; index--)
            if (result.charAt(index) == '0')
                break;

        return result.insert(index + 1, sb).toString();
    }
}