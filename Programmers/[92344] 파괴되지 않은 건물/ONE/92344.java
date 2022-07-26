class Solution {
    public int solution(int[][] board, int[][] skill) {
        int answer = 0;
        int n = board.length;
        int m = board[0].length;
        int[][] cumulative = new int[n + 1][m + 1];

        for (int[] s : skill) {
            int degree = s[0] == 1 ? -s[5] : s[5];
            int r1 = s[1];
            int c1 = s[2];
            int r2 = s[3];
            int c2 = s[4];

            cumulative[r1][c1] += degree;
            cumulative[r1][c2 + 1] -= degree;
            cumulative[r2 + 1][c1] -= degree;
            cumulative[r2 + 1][c2 + 1] += degree;
        }

        // 가로 누적합
        for (int i = 0; i <= n; i++) {
            int sum = 0;
            for (int j = 0; j <= m; j++) {
                sum += cumulative[i][j];
                cumulative[i][j] = sum;
            }
        }

        // 세로 누적합
        for (int j = 0; j < m; j++) {
            int sum = 0;
            for (int i = 0; i < n; i++) {
                sum += cumulative[i][j];
                cumulative[i][j] = sum;
            }
        }

        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                if (board[i][j] + cumulative[i][j] > 0)
                    answer++;

        return answer;
    }
}