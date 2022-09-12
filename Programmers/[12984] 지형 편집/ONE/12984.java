class Solution {
    public long solution(int[][] land, int P, int Q) {
        long start = Long.MAX_VALUE, end = Long.MIN_VALUE, mid = 0;

        for (int[] ints : land)
            for (int i : ints) {
                end = Math.max(end, i);
                start = Math.min(start, i);
            }

        while (start <= end) {
            mid = (start + end) / 2;

            if (start == end)
                break;

            long cost1 = getCost(mid, land, P, Q);
            long cost2 = getCost(mid + 1, land, P, Q);

            if (cost1 < cost2)
                end = mid;
            else
                start = mid + 1;
        }

        return getCost(mid, land, P, Q);
    }

    long getCost(long mid, int[][] land, int P, int Q) {
        long result = 0;
        for (int[] ints : land) {
            for (int i : ints) {
                if (i > mid) {
                    result += (i - mid) * Q;
                } else if (i < mid) {
                    result += (mid - i) * P;
                }
            }
        }
        return result;
    }
}