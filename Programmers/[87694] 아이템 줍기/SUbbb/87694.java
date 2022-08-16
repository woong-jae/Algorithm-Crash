import java.util.*;

class Coor {
    int x;
    int y;
    
    public Coor(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

class Solution {
    private static int[][] map = new int[101][101];
    private static int answer = Integer.MAX_VALUE;
    
    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        for (int[] rect : rectangle)
            fillMap(rect);
        
        bfs(characterX * 2, characterY * 2, itemX * 2, itemY * 2);
        return answer/2;
    }
    
    // 주어진 사각형 정보를 이용해 테두리와 내부 정보를 map에 표시
    private void fillMap(int[] rect) {
        int x1 = rect[0] * 2;
        int y1 = rect[1] * 2;
        int x2 = rect[2] * 2;
        int y2 = rect[3] * 2;
        
        // 내부 색칠
        // -1 : 내부
        // 1 : 테두리
        for (int i = x1; i <= x2; i++) {
            for (int j = y1; j <= y2; j++) {
                if (map[i][j] == -1) continue;
                map[i][j] = -1;
                if (i == x1 || i == x2 || j == y1 || j == y2) map[i][j] = 1;
            }
        }       
    }
    
    private void bfs(int cX, int cY, int iX, int iY) {
        int[][] dir = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
        boolean[][] visited = new boolean[101][101];
        Queue<Coor> queue = new LinkedList<>();
        queue.add(new Coor(cX, cY));
        
        while(!queue.isEmpty()) {
            Coor c = queue.poll();
            int curX = c.x;
            int curY = c.y;
            
            for (int[] d : dir) {
                int nX = curX + d[0];
                int nY = curY + d[1];
                
                if (!isValid(nX, nY)) continue;
                if (map[nX][nY] != 1 || visited[nX][nY]) continue;
                
                // map에 비용 저장
                map[nX][nY] = map[curX][curY] + 1;
                if (nX == iX && nY == iY) {
                    // 이미 저장된 값이 있다면, 더 최소 값을 저장
                    answer = Math.min(answer, map[nX][nY]);
                    continue;
                }
                
                visited[nX][nY] = true;
                queue.add(new Coor(nX, nY));
            }
        }
    }
    
    private boolean isValid(int i, int j) {
        return i >= 0 && i < 101 && j >= 0 && j < 101;
    }
}