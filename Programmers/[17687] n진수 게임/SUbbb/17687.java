
class Solution {
    public String solution(int n, int t, int m, int p) {
        String[] answer = new String[t];
        int num = 0;
        int idx = 0;
        int order = 1;

        while (idx != t) {
            String strNum = Integer.toString(num, n);

            for (int i = 0; i < strNum.length() && idx != t; i++) {
                if (order == p) answer[idx++] = String.valueOf(strNum.charAt(i)).toUpperCase();
                order++;
                if (order == m + 1) order = 1;
            }
            num++;
        }
        
        return String.join("", answer);
    }
}