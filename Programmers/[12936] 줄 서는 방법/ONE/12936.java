import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public int[] solution(int n, long k) {
        int[] result = new int[n];
        long count = 1;
        List<Integer> candidate = new ArrayList<>();

        for (int num = 1; num <= n; num++) {
            count *= num;
            candidate.add(num);
        }

        k--;
        int index = 0;
        while (n > 0) {
            count /= n;
            int value = (int) (k / count);
            result[index++] = candidate.get(value);
            candidate.remove(value);
            k %= count;
            n--;
        }

        return result;
    }
}
