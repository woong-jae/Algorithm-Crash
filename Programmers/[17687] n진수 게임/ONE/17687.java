import java.util.HashMap;
import java.util.Map;

class Solution {
    public String solution(int n, int t, int m, int p) {
        StringBuilder answer = new StringBuilder();
        StringBuilder sb = new StringBuilder();
        Map<Integer, String> map = initMap();

        int number = 0;
        while (sb.length() < m * t) {
            int k = maxExp(n, number);
            int tmp = number;

            for (int i = k; k > 0; k--) {
                if (i > 1)
                    sb.append(map.get(tmp / (int) Math.pow(n, k - 1)));
                else
                    sb.append(map.get(tmp % (int) Math.pow(n, k)));
                tmp = tmp % (int) Math.pow(n, k - 1);
            }
            number++;
        }

        for (int i = 0; i < t; i++)
            answer.append(sb.charAt(i * m + p - 1));

        return answer.toString();
    }

    private Map<Integer, String> initMap() {
        Map<Integer, String> map = new HashMap<>();

        for (int i = 0; i < 16; i++){
            if(i >= 10)
                map.put(i, String.valueOf((char) (i + 55)));
            else
                map.put(i, String.valueOf(i));
        }

        return map;
    }

    private int maxExp(int n, int number) {
        int k = 1;

        while (number / (int) Math.pow(n, k) != 0)
            k++;

        return k;
    }
}