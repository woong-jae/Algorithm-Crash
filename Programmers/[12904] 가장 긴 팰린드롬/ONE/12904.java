class Solution {
    public int solution(String s) {
        int n = s.length();
        int answer = 1;

        for (int i = 0; i < n - 1; i++) {
            int len = 1;
            int left = i - 1, right = i + 1;

            answer = Math.max(answer, getAnswer(s, n, len, left, right));

            if (s.charAt(i) == s.charAt(i + 1)) {
                len = 2;
                left = i - 1;
                right = i + 2;

                answer = Math.max(answer, getAnswer(s, n, len, left, right));
            }
        }
        return answer;
    }

    private int getAnswer(String s, int n, int len, int left, int right) {
        while (left >= 0 && right < n) {
            if (s.charAt(left) != s.charAt(right))
                break;
            len += 2;
            left -= 1;
            right += 1;
        }
        return len;
    }
}