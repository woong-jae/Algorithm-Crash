import java.util.Arrays;

class Solution {
    public int solution(int[] a) {
        int n = a.length, answer = 2;
        int[] leftMin = Arrays.copyOf(a, n);
        int[] rightMin = Arrays.copyOf(a, n);

        for (int i = 1; i < n; i++) {
            leftMin[i] = Math.min(leftMin[i - 1], leftMin[i]);
            rightMin[n - 1 - i] = Math.min(rightMin[n - 1 - i], rightMin[n - i]);
        }

        for (int i = 1; i < n - 1; i++) {
            if (a[i] > leftMin[i - 1] && a[i] > rightMin[i + 1])
                continue;
            answer++;
        }

        return answer;
    }
}