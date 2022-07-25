class Solution {
    class Result{
        boolean win;
        int count;

        public Result(boolean win, int count){
            this.win = win;
            this.count = count;
        }
    }

    private int r, c;
    private static int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    public int solution(int[][] board, int[] aloc, int[] bloc) {
        r = board.length;
        c = board[0].length;
        return dfs(aloc[0], aloc[1], bloc[0], bloc[1], 0, board).count;
    }

    public Result dfs(int curX, int curY, int anotherX, int anotherY, int count, int[][] board){
        boolean win = false;
        int minCount = 5 * 5;
        int maxCount = count;

        if(board[curX][curY] == 1){
            for(int[] tmp : dir){
                int x1Tmp = curX + tmp[0];
                int y1Tmp = curY + tmp[1];
                if(isValid(x1Tmp, y1Tmp, board)){
                    board[curX][curY] = 0;
                    Result d = dfs(anotherX, anotherY, x1Tmp, y1Tmp, count + 1, board);
                    win |= !d.win;
                    if (d.win)
                        maxCount = Math.max(maxCount, d.count);
                    else
                        minCount = Math.min(minCount, d.count);
                    board[curX][curY] = 1;
                }
            }
        }

        return new Result(win, win ? minCount : maxCount);
    }

    public boolean isValid(int x, int y, int[][] board){
        return x >= 0 && x < r && y >= 0 && y < c && board[x][y] != 0;
    }
}