import java.util.Arrays;

class Solution {
    public int solution(int n, int[] cores) {
        int min = 0;
        int max = cores[0] * n;

        int time = 0;
        int workload = 0;
        while (min <= max) {
            int mid = (min + max) / 2;
            int count = calculateWorkload(mid, cores);
            if (count >= n) {
                max = mid - 1;
                time = mid;
                workload = count;
            } else {
                min = mid + 1;
            }
        }

        workload -= n;
        for (int index = cores.length - 1; index >= 0; index--) {
            if (time % cores[index] == 0) {
                if (workload == 0) {
                    return index + 1;
                }
                workload--;
            }
        }
        return -1;
    }

    private int calculateWorkload(int time, int[] cores) {
        if (time == 0) {
            return cores.length;
        }
        int count = cores.length;
        for (int core : cores) {
            count += (time / core);
        }
        return count;
    }
}