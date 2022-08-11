import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

class Solution {
    class Node {
        int x;
        int y;
        int distance;

        public Node(int x, int y, int distance) {
            this.x = x;
            this.y = y;
            this.distance = distance;
        }
    }

    private int answer;
    private int[] dx = {-1, 0, 1, 0, -1, -1, 1, 1};
    private int[] dy = {0, 1, 0, -1, -1, 1, -1, 1};
    private boolean[][] map = new boolean[102][102];
    private boolean[][] outLineMap = new boolean[102][102];
    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {

        // 사각형으로 boolean map 그리기
        initMap(rectangle);
        // 사각형의 바깥 변을 따라 선을 따라 최단거리를 구하기
        bfs(2 * characterY, 2 * characterX, 2 * itemY, 2 * itemX);

        return answer / 2;
    }

    private void initMap(int[][] rectangle) {
        for (int[] r : rectangle)
            for (int x = 2 * r[1]; x <= 2 * r[3]; x++)
                for (int y = 2 * r[0]; y <= 2 * r[2]; y++)
                    map[x][y] = true;
    }

    private boolean inbound(int x, int y) {
        return x >= 0 && y >= 0 && x < 102 && y < 102;
    }

    private boolean isOutLine(int x, int y) {
        if (!map[x][y])
            return false;

        for (int d = 0; d < 8; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];
            if (inbound(nx, ny) && !map[nx][ny])
                return true;
        }
        return false;
    }

    private void bfs(int cx, int cy, int ix, int iy) {
        Queue<Node> queue = new PriorityQueue<>(Comparator.comparingInt(n -> n.distance));

        queue.add(new Node(cx, cy, 0));
        outLineMap[cx][cy] = true;

        while (!queue.isEmpty()) {
            Node current = queue.poll();

            if (current.x == ix && current.y == iy) {
                answer = current.distance;
                return;
            }

            for (int d = 0; d < 4; d++) {
                int nx = current.x + dx[d];
                int ny = current.y + dy[d];
                if (inbound(nx, ny) && isOutLine(nx, ny) && !outLineMap[nx][ny]) {
                    outLineMap[nx][ny] = true;
                    queue.add(new Node(nx, ny, current.distance + 1));
                }
            }
        }
    }
}