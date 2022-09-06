class Solution {
    public int solution(int n) {
        // 피보나치 수열의 규칙
        int[] fibonacci = new int[n + 1];
        fibonacci[0] = 1;
        fibonacci[1] = 1;

        for (int i = 2; i <= n; i++)
            fibonacci[i] = (fibonacci[i - 2] + fibonacci[i - 1]) % 1000000007;

        return fibonacci[n];
    }
}