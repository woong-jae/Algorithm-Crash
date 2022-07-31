class Solution {
    public long solution(int a, int b, int[] g, int[] s, int[] w, int[] t) {
        int n = g.length;
        long answer = (long) ((long) 1e5 * 2 * 1e9 * 2);
        long left = 0;
        long right = answer;

        while (left <= right) {
            long mid = (left + right) / 2;

            long gold = 0;
            long silver = 0;
            long sum = 0;
            long totalWeight;

            for (int i = 0; i < n; i++) {
                long moveCount = (long) Math.ceil(((double) (mid / t[i]) / 2));
                totalWeight = moveCount * w[i];
                gold += Math.min(g[i], totalWeight);
                silver += Math.min(s[i], totalWeight);
                sum += Math.min(g[i] + s[i], totalWeight);
            }

            if (gold >= a && silver >= b && sum >= a + b) {
                right = mid - 1;
                answer = Math.min(mid, answer);
            }
            else
                left = mid + 1;
        }
        return answer;
    }
}