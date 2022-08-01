import java.util.*;

class Solution {
    private Map<String, Integer> sellerMoney = new HashMap<>();
    private Map<String, String> parent = new HashMap<>();
    private int len;

    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        len = enroll.length;
        initParent(enroll, referral);
        
        for (int i = 0; i < seller.length; i++)
            updateSellerMoney(seller[i], 100 * amount[i]);

        int[] answer = new int[len];
        for (int i = 0; i < len; i++)
            answer[i] = sellerMoney.getOrDefault(enroll[i], 0);
        
        return answer;
    }

    private void updateSellerMoney(String p, int money) {
        while(true) {
            if (p.equals("-") || money == 0) break;
            
            int dist = money / 10;
            sellerMoney.put(p, money - dist + sellerMoney.getOrDefault(p, 0));     
            p = parent.get(p);
            money = dist;
        }
    }

    private void initParent(String[] enroll, String[] referral) {
        for (int i = 0; i < len; i++)
            parent.put(enroll[i], referral[i]);
    }
}
