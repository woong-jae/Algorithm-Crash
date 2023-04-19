class Solution {
    public int[] solution(int n, int s) {
        if (n > s) {
            return new int[]{-1};
        }

        int[] answer = new int[n];
        int basic = s / n;
        int remain = s % n;

        for (int index = 0; index < n; index++) {
            answer[index] = basic;
            if (index >= n - remain) {
                answer[index]++;
            }
        }

        return answer;
    }
}