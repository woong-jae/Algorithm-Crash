class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long sumOfDistance = 0L;
        int deliver = 0;
        int pickup = 0;
        for (int index = n - 1; index >= 0; index--) {
            if (deliveries[index] == 0 && pickups[index] == 0) {
                continue;
            }
            int count = 0;
            while (deliver < deliveries[index] || pickup < pickups[index]) {
                ++count;
                deliver += cap;
                pickup += cap;
            }
            deliver -= deliveries[index];
            pickup -= pickups[index];
            sumOfDistance += (index + 1) * 2L * count;
        }
        return sumOfDistance;
    }
}
