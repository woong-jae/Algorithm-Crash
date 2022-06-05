class Solution {
    public int solution(int[][] board) {
        int answer = 0;
        int n = board.length;
        int count;

        do {
            count = 0;

            for (int i = 0; i < n; i++)
                for (int j = 0; j < n; j++) {
                    if (i < n - 1 && j < n - 2 && find(i, j, 2, 3, board))
                        count++;
                    else if (i < n - 2 && j < n - 1 && find(i, j, 3, 2, board))
                        count++;
                }
            answer += count;

        } while (count != 0);

        return answer;
    }

    private boolean find(int row, int col, int h, int w, int[][] board) {
        int emptyCount = 0;
        int value = -1;

        for (int r = row; r < row + h; r++)
            for (int c = col; c < col + w; c++) {
                if (board[r][c] == 0) {
                    if (!canFill(r, c, board))
                        return false;
                    if (++emptyCount > 2)
                        return false;
                }
                else {
                    if(value != -1 && value != board[r][c])
                        return false;
                    value = board[r][c];
                }
            }

        erase(row, col, h, w, board);

        return true;
    }

    private boolean canFill(int r, int c, int[][] board) {
        for(int i = 0; i < r; i++)
            if(board[i][c] != 0)
                return false;
        return true;
    }

    private void erase(int row, int col, int h, int w, int[][] board) {
        for (int r = row; r < row + h; r++)
            for (int c = col; c < col + w; c++)
                board[r][c] = 0;
    }
}