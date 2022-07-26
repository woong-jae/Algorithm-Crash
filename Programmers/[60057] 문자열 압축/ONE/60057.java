class Solution {
    public int solution(String s) {
        int answer = Integer.MAX_VALUE;
        int len = s.length();

        for (int i = 1; i <= (len + 1) / 2; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j <= len - i; j += i) {
                String sub = s.substring(j, j + i);
                int count = 1;
                for (int k = j + i; k <= len - i; k += i) {
                    if (sub.equals(s.substring(k, k + i))) {
                        count++;
                        j = k;
                    }
                    else
                        break;
                }
                if(count == 1)
                    sb.append(sub);
                else
                    sb.append(count).append(sub);
            }
            sb.append(s.substring(len - (len % i)));
            answer = Math.min(answer, sb.length());
        }
        return answer;
    }
}