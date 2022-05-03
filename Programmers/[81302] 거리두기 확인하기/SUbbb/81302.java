import java.util.*;

class Loc {
    int x;
    int y;
    int dist;
    
    public Loc(int x, int y, int dist) {
        this.x = x;
        this.y = y;
        this.dist = dist;
    }
}

class WaitingRoom {
    String[] place;
    int[] rangeX = { 0, 1, 0, -1 };
    int[] rangeY = { 1, 0, -1, 0 };
    
    public WaitingRoom(String[] place) {
        this.place = place;
    }
    
    public int checkKeepingDistance() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                char p = place[i].charAt(j);
                if (p == 'P') 
                    if (bfs(i, j) == 0) 
                        return 0;
            }
        }
        return 1;
    }
    
    private int bfs(int x, int y) {
        Queue<Loc> queue = new LinkedList<>();
        boolean[][] visited = new boolean[5][5];
        int keep = 1;
        visited[x][y] = true;
        queue.add(new Loc(x, y, 0));

        while(!queue.isEmpty()) {
            Loc now = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nx = now.x + rangeX[i];
                int ny = now.y + rangeY[i];
                if (!isIn(nx, ny)) continue;
                if (visited[nx][ny]) continue;
                
                int nDist = now.dist + 1;
                if (nDist > 2) continue;

                char nP = place[nx].charAt(ny);
                if (nP == 'P') return 0;
                if (nP == 'X') continue;

                visited[nx][ny] = true;
                queue.add(new Loc(nx, ny, nDist));
            }
        }

        return keep;
    }
    
    private boolean isIn(int x, int y) {
        return (x < 5 && x >= 0 && y < 5 && y >= 0);
    }
}

class Solution {
    public int[] solution(String[][] places) {
        int[] answer = new int[5];
        int T = 0;
        
        while (T < 5) {
            WaitingRoom wr = new WaitingRoom(places[T]);
            answer[T++] = wr.checkKeepingDistance();
        }
        
        return answer;
    }
}