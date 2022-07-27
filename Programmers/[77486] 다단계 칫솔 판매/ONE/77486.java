import java.util.*;

class Solution {
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        int n = enroll.length;
        int sellerLen = seller.length;
        int[] answer = new int[n];
        Map<String, String> parent = new HashMap<>();
        Map<String, Integer> index = new HashMap<>();

        for (int i = 0; i < n; i++) {
            parent.put(enroll[i], referral[i]);
            index.put(enroll[i], i);
        }

        for (int i = 0; i < sellerLen; i++) {
            String current = seller[i];
            // 벌어들인 순수익
            int revenue = amount[i] * 100;

            while (!current.equals("-")) {
                int parentProfit = revenue / 10;
                int currentProfit = revenue - parentProfit;

                answer[index.get(current)] += currentProfit;

                current = parent.get(current);
                revenue /= 10;

                if(revenue < 1)
                    break;
            }
        }
        return answer;
    }
}