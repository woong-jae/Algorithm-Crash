class Solution {
    public int solution(int[] stones, int k) {
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;

        for (int stone : stones) {
            max = Math.max(max, stone);
            min = Math.min(min, stone);
        }

        return parametricSearch(stones, k, min, max);
    }

    private int parametricSearch(int[] stones, int k, int low, int high) {
        if (low == high)
            return low;

        while (low < high) {
            int mid = (low + high) / 2;

            if (cross(stones, k, mid))
                low = mid + 1;
            else
                high = mid;
        }

        return low - 1;
    }

    private boolean cross(int[] stones, int k, int mid) {
        int cnt = 0;

        for (int stone : stones) {
            if (stone - mid < 0)
                cnt++;
            else
                cnt = 0;

            if (cnt == k)
                return false;
        }
        return true;
    }
}