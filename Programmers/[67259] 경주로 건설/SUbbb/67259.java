
class Solution {
    int[] xDir = { 0, 1, 0, -1 };
    int[] yDir = { 1, 0, -1, 0 };
    int[][] costs;
    int n;
    int minCost = Integer.MAX_VALUE;
    
    public int solution(int[][] board) {
        costs = new int[board.length][board.length];
        n = board.length;
        
        dfs(board, -1, 0, 0);
        
        return minCost;
    }
    
    private void dfs(int[][] board, int prevDir, int x, int y) {
        if (x == n - 1 && y == n - 1) {
            minCost = Math.min(costs[x][y], minCost);
            return;
        }
        
        for (int i = 0; i < 4; i++) {
            int nx = x + xDir[i];
            int ny = y + yDir[i];
            
            if (nx == 0 && ny == 0) continue;
            if (!checkBoundary(nx, ny)) continue;
            if (board[nx][ny] == 1) continue;
            
            int tmp = costs[x][y];
            if (prevDir != -1 && Math.abs(prevDir - i) % 2 == 1) 
                tmp += 500;
            tmp += 100;
                
            if ((costs[nx][ny] != 0 && costs[nx][ny] >= tmp) || costs[nx][ny] == 0) {
                costs[nx][ny] = tmp;
                dfs(board, i, nx, ny);
            }
        }
    }
    
    private boolean checkBoundary(int x, int y) {
        return (x < n && x >= 0 && y < n && y >= 0);
    }
}