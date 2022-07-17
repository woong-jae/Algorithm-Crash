import java.util.*;

class Result {
    boolean win;
    int count;
    
    public Result(boolean win, int count) {
        this.win = win;
        this.count = count;
    }
}

class Solution {
    static int[][] dir = {{ -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 }};
    static int[][] board;
    static int N;
    static int M;
    
    public int solution(int[][] board, int[] aloc, int[] bloc) {
        this.board = board;
        this.N = board.length;
        this.M = board[0].length;
        
        return dfs(aloc[0], aloc[1], bloc[0], bloc[1], 0, 0).count;
    }
    
    private Result dfs(int aX, int aY, int bX, int bY, int moveA, int moveB) {
        boolean win = false;
        int minCount = 5 * 5;
        int maxCount = moveA + moveB;
        
        if (moveA == moveB && board[aX][aY] == 1) {
            // a가 움직일 차례
            for (int[] d : dir) {
                int naX = aX + d[0];
                int naY = aY + d[1];

                if (!checkBoundary(naX, naY)) continue;
                
                board[aX][aY] = 0;
                Result r = dfs(naX, naY, bX, bY, moveA + 1, moveB);
                win |= !r.win;
                if (!r.win) minCount = Math.min(minCount, r.count);
                else maxCount = Math.max(maxCount, r.count);

                board[aX][aY] = 1;
            }
        } else if (moveA > moveB && board[bX][bY] == 1) {
            // b가 움직일 차례
            for (int[] d : dir) {
                int nbX = bX + d[0];
                int nbY = bY + d[1];

                if (!checkBoundary(nbX, nbY)) continue;
                
                board[bX][bY] = 0;
                // dfs의 결과는 본인이 아닌 다른 사람의 결과이므로, 승패가 반대이다.
                // 또한 r.win = true인 경우가 하나라도 있을 경우 win은 true가 되어야 하므로 or연산을 수행한다.
                Result r = dfs(aX, aY, nbX, nbY, moveA, moveB + 1);
                win |= !r.win;

                // 본인이 이기는 경우라면 최대한 빨리 이길 때의 이동횟수를 갱신한다.
                if (!r.win) minCount = Math.min(minCount, r.count);
                // 본인이 지는 경우라면 최대한 오래 버틸 때의 이동횟수를 갱신한다.
                else maxCount = Math.max(maxCount, r.count);

                board[bX][bY] = 1;
            }
        }
        
        return new Result(win, win ? minCount : maxCount);
    }
    
    private boolean checkBoundary(int x, int y) {
        return (x >= 0 && x < N && y >= 0 && y < M && board[x][y] != 0) ;
    }
}