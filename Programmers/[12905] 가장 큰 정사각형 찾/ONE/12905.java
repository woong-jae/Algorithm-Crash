import java.util.Arrays;

class Solution {
    public int solution(int[][] board) {
        int n = board.length;
        int m = board[0].length;

        for (int row = 1; row < n; row++) {
            for (int col = 1; col < m; col++) {
                if (board[row][col] == 1) {
                    board[row][col] = Math.min(board[row - 1][col - 1],
                            Math.min(board[row][col - 1], board[row - 1][col])) + 1;
                }
            }
        }

        int max = Arrays.stream(board)
                .flatMapToInt(Arrays::stream)
                .max()
                .getAsInt();

        return max * max;
    }
}