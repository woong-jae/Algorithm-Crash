import java.util.HashMap;
import java.util.Map;

class Solution {
    private final int DAYS = 10;
    private final Map<String, Integer> wants = new HashMap<>();
    private final Map<String, Integer> sales = new HashMap<>();

    public int solution(String[] want, int[] number, String[] discount) {

        for (int index = 0; index < want.length; index++) {
            wants.put(want[index], number[index]);
        }

        int count = 0;
        int length = discount.length;
        for (int day = 0; day < length; day++) {
            sales.put(discount[day], sales.getOrDefault(discount[day], 0) + 1);
            if (day < DAYS - 1) {
                continue;
            }
            if (day - DAYS >= 0) {
                if (sales.get(discount[day - DAYS]) <= 1) {
                    sales.remove(discount[day - DAYS]);
                } else {
                    sales.put(discount[day - DAYS], sales.get(discount[day - DAYS]) - 1);
                }
            }
            if (wants.equals(sales)) {
                count++;
            }
        }
        return count;
    }
}