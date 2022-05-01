import java.util.PriorityQueue;
import java.util.Queue;

class Road implements Comparable<Road> {

    int row;
    int col;
    int dir;
    int cost;

    public Road(int row, int col, int dir, int cost) {
        this.row = row;
        this.col = col;
        this.dir = dir;
        this.cost = cost;
    }

    @Override
    public int compareTo(Road o) {
        return this.cost - o.cost;
    }
}

class Solution {
    private int N, answer;
    private int[][][] visited; //4 방향별 방문 정보
    private int[][] dir = { {-1,0}, {1,0}, {0,-1}, {0,1} }; // 상하좌우
    public int solution(int[][] board) {
        N = board.length;
        visited = new int[N][N][4];
        Queue<Road> queue = new PriorityQueue<>();

        visited[0][0][0] = visited[0][0][1] = visited[0][0][2] = visited[0][0][3] = -1;

        if (board[0][1] == 0) {
            queue.add(new Road(0, 1, 3, 100));
            visited[0][1][0] = 1;
        }

        if (board[1][0] == 0) {
            queue.add(new Road(1, 0, 1, 100));
            visited[1][0][1] = 1;
        }

        while (!queue.isEmpty()) {
            Road cur = queue.poll();

            if (cur.row == N - 1 && cur.col == N - 1) {
                answer = cur.cost;
                break;
            }

            for (int d = 0; d < 4; d++) {
                int nrow = cur.row + dir[d][0];
                int ncol = cur.col + dir[d][1];
                int ncost = cur.cost + ((cur.dir == d) ? 100 : 600);

                if (canMake(nrow, ncol, board) && !alreadyVisit(nrow, ncol, ncost, d)) {
                    visited[nrow][ncol][d] = ncost;
                    queue.add(new Road(nrow, ncol, d, ncost));
                }
            }
        }
        return answer;
    }

    private boolean canMake(int nrow, int ncol, int[][] board) {
        return nrow >= 0 && ncol >=0 && nrow < N && ncol < N && board[nrow][ncol] != 1;
    }

    private boolean alreadyVisit(int nrow, int ncol, int ncost, int dir) {
        return visited[nrow][ncol][dir] != 0 && visited[nrow][ncol][dir] < ncost;
    }
}