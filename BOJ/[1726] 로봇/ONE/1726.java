import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {
    static class Node {
        int x;
        int y;
        int dir;
        int count;

        public Node(int x, int y, int dir, int count) {
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.count = count;
        }

        public boolean isSame(Node o) {
            return this.x == o.x && this.y == o.y && this.dir == o.dir;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()), M = Integer.parseInt(st.nextToken());
        int[][] map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++)
                map[i][j] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        Node start = new Node(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1,
                Integer.parseInt(st.nextToken()) - 1, 0);
        st = new StringTokenizer(br.readLine());
        Node dest = new Node(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1,
                Integer.parseInt(st.nextToken()) - 1, 0);

        bfs(N, M, start, dest, map);
    }

    private static void bfs(int N, int M, Node start, Node dest, int[][] map) {
        int[] dx = {0, 0, 1, -1};
        int[] dy = {1, -1, 0, 0};
        boolean[][][] visited = new boolean[N][M][4];
        Queue<Node> queue = new PriorityQueue<>(Comparator.comparingInt(node -> node.count));
        queue.add(start);
        visited[start.x][start.y][start.dir] = true;

        while (!queue.isEmpty()) {
            Node current = queue.poll();

            if (current.isSame(dest)) {
                System.out.println(current.count);
                return;
            }

            if (!visited[current.x][current.y][turnRight(current.dir)]) {
                visited[current.x][current.y][turnRight(current.dir)] = true;
                queue.add(new Node(current.x, current.y, turnRight(current.dir),
                        current.count + 1));
            }
            if (!visited[current.x][current.y][turnLeft(current.dir)]) {
                visited[current.x][current.y][turnLeft(current.dir)] = true;
                queue.add(new Node(current.x, current.y, turnLeft(current.dir),
                        current.count + 1));
            }

            for (int k = 1; k <= 3; k++) {
                int nx = current.x + dx[current.dir] * k;
                int ny = current.y + dy[current.dir] * k;

                if (inbound(N, M, nx, ny) && !visited[nx][ny][current.dir]) {
                    if (map[nx][ny] == 0) {
                        visited[nx][ny][current.dir] = true;
                        queue.add(new Node(nx, ny, current.dir, current.count + 1));
                    }
                    else
                        break;
                }
            }
        }
    }

    private static boolean inbound(int N, int M, int x, int y) {
        return x >= 0 && y >= 0 && x < N && y < M;
    }

    private static int turnRight(int dir) {
        if (dir == 0)
            return 2;
        else if (dir == 1)
            return 3;
        else if (dir == 2)
            return 1;
        else
            return 0;
    }

    private static int turnLeft(int dir) {
        if (dir == 0)
            return 3;
        else if (dir == 1)
            return 2;
        else if (dir == 2)
            return 0;
        else
            return 1;
    }
}