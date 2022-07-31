import java.util.*;

class Solution {
    class Node {
        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    class Edge {
        int u;
        int v;
        int w;

        public Edge(int u, int v, int w) {
            this.u = u;
            this.v = v;
            this.w = w;
        }
    }

    private int n, height;
    private int[] parent;
    private int[] dx = {-1, 0, 1, 0};
    private int[] dy = {0, -1, 0, 1};
    private int[][] land;
    private int[][] check;
    private Queue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.w));
    public int solution(int[][] land, int height) {
        int answer = 0;
        int checkNum = 1;

        this.n = land.length;
        this.height = height;
        this.land = land;
        this.check = new int[n][n];

        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                if (check[i][j] == 0)
                    bfs(i, j, checkNum++);

        parent = new int[checkNum + 1];
        for (int i = 0; i < checkNum; i++)
            parent[i] = i;


        while (!pq.isEmpty()) {
            Edge edge = pq.poll();

            if ((find(edge.u) != find(edge.v)) && (edge.u != 0 && edge.v != 0)) {
                union(edge.u, edge.v);
                answer += edge.w;
            }
        }
        return answer;
    }

    private boolean inbound(int x, int y) {
        return x >= 0 && y >= 0 && x < n && y < n;
    }

    private int find(int n) {
        if (parent[n] == n)
            return n;
        return parent[n] = find(parent[n]);
    }

    private void union(int a, int b) {
        int parentA = find(a);
        int parentB = find(b);

        if (parentA == parentB)
            return;

        if(parentA < parentB)
            parent[parentB] = parentA;
        else
            parent[parentA] = parentB;
    }

    private void bfs(int x, int y, int checkNum) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(x, y));
        check[x][y] = checkNum;

        while (!queue.isEmpty()) {
            Node current = queue.poll();

            for (int d = 0; d < 4; d++) {
                int nx = current.x + dx[d];
                int ny = current.y + dy[d];

                if (inbound(nx, ny)){
                    int diff = Math.abs(land[current.x][current.y] - land[nx][ny]);

                    if (diff > height) {
                        if (check[nx][ny] > 0)
                            pq.add(new Edge(check[current.x][current.y], check[nx][ny], diff));
                        continue;
                    }
                    if (check[nx][ny] == 0) {
                        check[nx][ny] = checkNum;
                        queue.add(new Node(nx, ny));
                    }
                }
            }
        }
    }
}