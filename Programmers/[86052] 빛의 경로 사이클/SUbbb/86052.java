import java.util.*;

class Solution {
    private int m;
    private int n;
    private boolean[][][] visited;
    private String[] grid;
    // 0: 아래, 1: 왼, 2: 위, 3: 오른
    private int[][] dir = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
        
    public int[] solution(String[] grid) {
        m = grid.length;
        n = grid[0].length();
        visited = new boolean[m][n][4];
        this.grid = grid;
        
        ArrayList<Integer> answer = new ArrayList<>();
        
        // 모든 격자에서 4방향으로 다 방문
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                for (int d = 0; d < 4; d++)
                    if (!visited[i][j][d])
                        answer.add(go(i, j, d));
        
        return answer.stream().sorted().mapToInt(i -> i).toArray();
    }
    
    private int go(int x, int y, int d) {
        // 이동 거리
        int cnt = 0;
 
        while (!visited[x][y][d]) {
            cnt++;
            visited[x][y][d] = true;
 
            if (grid[x].charAt(y) == 'L')
                d = d == 0 ? 3 : d - 1; // 좌회전
            else if (grid[x].charAt(y) == 'R')
                d = d == 3 ? 0 : d + 1; // 우회전
            
            // 다음 좌표 계산
            x = (x + dir[d][0] + m) % m;
            y = (y + dir[d][1] + n) % n;
        }
 
        return cnt;
    }
}