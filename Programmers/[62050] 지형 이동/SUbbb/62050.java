import java.util.*;

class Node {
    int x;
    int y;
    
    public Node(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

class Square implements Comparable<Square> {
    int from;
    int to;
    int diffDist;
    
    public Square(int from, int to, int diffDist) {
        this.from = from;
        this.to = to;
        this.diffDist = diffDist;
    }
    
    @Override
    public int compareTo(Square s) {
        return Integer.compare(this.diffDist, s.diffDist);
    }
}

class Solution {
    private static int N;
    private static int[] parent;
    private static Queue<Square> pq = new PriorityQueue<>();
    private static int[][] visited;
    private static int[][] land;
    private static int height;
    private static int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    private static int areaN;
    
    public int solution(int[][] land, int height) {
        this.N = land.length;
        this.height = height;
        this.land = land;
        
        this.visited = new int[N][N];
        
        for (int i = 0; i < N; i++) 
            for (int j = 0; j < N; j++)
                if (visited[i][j] == 0) {
                    areaN++;
                    bfs(i, j);
                }
        
        this.parent = new int[areaN + 1];
        for (int i = 0; i <= areaN; i++) parent[i] = i;
        
        int answer = 0;
        while(!pq.isEmpty()) {
            Square s = pq.poll();
            int f = s.from;
            int t = s.to;
            int d = s.diffDist;
            
            // 같은 부모, 즉 사이클을 형성하거나, 혹은 방문한 적 없는 지형이라면 패스
            if (find(f) == find(t) || (f == 0 || t == 0)) continue;
            else {
                // 부모를 같게, 즉 사다리를 놓고 이동했음을 의미!
                merge(f, t);
                answer += d;
            }
        }
        
        return answer;
    }
    
    // 이동 가능한 지형에 번호 매기기
    private void bfs(int x, int y) {
        visited[x][y] = areaN;
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(x, y));
        
        while(!queue.isEmpty()) {
            Node node = queue.poll();
            int tmpX = node.x;
            int tmpY = node.y;
            
            for (int[] d : dir) {
                int nx = tmpX + d[0];
                int ny = tmpY + d[1];
                
                if (!isIn(nx, ny)) continue;
                
                int diff = Math.abs(land[tmpX][tmpY] - land[nx][ny]);
                // 지형 간의 차가 높이보다 크고, 이미 방문한 지역이라면 사다리를 놓아야 할 수도 있다.
                if (diff > height) {
                    if (visited[nx][ny] != 0) 
                        pq.offer(new Square(visited[tmpX][tmpY], visited[nx][ny], diff));
                    continue;
                }
                
                // 지형 간의 차가 높이보다 크지 않은 경우,
                // 방문한 지형이라면 패스
                if (visited[nx][ny] != 0) continue;
                // 방문한 적 없는 지형인 경우 번호 매기기
                else {
                    visited[nx][ny] = areaN;
                    queue.offer(new Node(nx, ny));
                }
            }
        }
    }
    
    private boolean isIn(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < N;
    }
    
    // 재귀를 이용해 부모 지형 번호 찾기
    private int find(int n) {
        if (parent[n] == n) return n;
        else return parent[n] = find(parent[n]);
    }
    
    private void merge(int n1, int n2) {
        n1 = find(n1);
        n2 = find(n2);
        
        if (n1 == n2) return;
        
        if (n1 > n2) parent[n1] = n2;
        else parent[n2] = n1;
    }
}