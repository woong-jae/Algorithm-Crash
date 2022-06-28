import java.util.LinkedList;
import java.util.Queue;

class Solution {
    private int n;
    private int[][] board;
    private int[] dx = {0, 1, 0, -1};
    private int[] dy = {1, 0, -1, 0};
    private int[] rdx = {-1, 1, 1, -1};
    private int[] rdy = {1, 1, -1, -1};
    public int solution(int[][] board) {
        this.n = board.length;
        this.board = board;

        return bfs();
    }

    private int bfs() {
        int x, y, dir, time, ox, oy;
        int nx, ny, nox, noy, ndir;
        int rx, ry;
        boolean[][][] visited = new boolean[n][n][4];
        Queue<Robot> queue = new LinkedList<>();

        queue.add(new Robot(0, 0, 0, 0));
        visited[0][0][0] = true;

        while (!queue.isEmpty()) {
            Robot robot = queue.poll();
            x = robot.x;
            y = robot.y;
            dir = robot.dir;
            time = robot.time;
            ox = robot.getOtherX();
            oy = robot.getOtherY();

            if (robot.isFinish())
                return time;

            for (int i = 0; i < 4; i++) {
                nx = x + dx[i];
                ny = y + dy[i];
                nox = ox + dx[i];
                noy = oy + dy[i];

                if (!inbound(nx, ny) || !inbound(nox, noy))
                    continue;
                if (board[nx][ny] == 1 || board[nox][noy] == 1)
                    continue;
                if (visited[nx][ny][dir])
                    continue;

                visited[nx][ny][dir] = true;
                queue.add(new Robot(nx, ny, dir, time + 1));
            }

            for (int i = 1; i < 4; i += 2) {
                ndir = (dir + i) % 4;
                nox = x + dx[ndir];
                noy = y + dy[ndir];

                int tempDir = (i == 1) ? ndir : dir;
                rx = x + rdx[tempDir];
                ry = y + rdy[tempDir];

                if (!inbound(nox, noy) || !inbound(rx, ry))
                    continue;
                if (board[nox][noy] == 1 || board[rx][ry] == 1)
                    continue;
                if (visited[x][y][ndir])
                    continue;

                visited[x][y][ndir] = true;
                queue.add(new Robot(x, y, ndir, time + 1));
            }

            dir = (dir + 2) % 4;
            for (int i = 1; i < 4; i += 2) {
                ndir = (dir + i) % 4;
                nx = ox + dx[ndir];
                ny = oy + dy[ndir];

                int tempDir = (i == 1) ? ndir : dir;
                rx = ox + rdx[tempDir];
                ry = oy + rdy[tempDir];

                ndir = (ndir + 2) % 4;
                if (!inbound(nx, ny) || !inbound(rx, ry))
                    continue;
                if (board[nx][ny] == 1 || board[rx][ry] == 1)
                    continue;
                if (visited[nx][ny][ndir])
                    continue;

                visited[nx][ny][ndir] = true;
                queue.add(new Robot(nx, ny, ndir, time + 1));
            }
        }
        return -1;
    }

    private boolean inbound(int x, int y) {
        return x >= 0 && y >= 0 && x < n && y < n;
    }

    private class Robot {
        int x, y;
        int dir;
        int time;

        Robot(int x, int y, int dir, int time) {
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.time = time;
        }

        public int getOtherX() {
            return x + dx[dir];
        }

        public int getOtherY() {
            return y + dy[dir];
        }

        public boolean isFinish() {
            return (x == n - 1 && y == n - 1)
                    || (getOtherX() == n - 1 && getOtherY() == n - 1);
        }
    }
}