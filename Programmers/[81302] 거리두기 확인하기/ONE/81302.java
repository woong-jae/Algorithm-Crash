import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

class Node {
    int x;
    int y;
    int depth;

    public Node(int x, int y, int depth) {
        this.x = x;
        this.y = y;
        this.depth = depth;
    }
}

class Solution {
    private final int N = 5;
    public int[] solution(String[][] places) {
        int[] answer = new int[N];

        for (int i = 0; i < N; i++)
            answer[i] = dfs(places[i]);

        return answer;
    }

    private boolean inBound(int nx, int ny, String[] room, boolean[][] visited) {
        return nx >= 0 && ny >= 0 && nx < N && ny < N && room[nx].charAt(ny) != 'X' && !visited[nx][ny];
    }

    private int dfs(String[] room) {

        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};제

        for(int i = 0; i < N; i++)
            for(int j = 0; j < N; j++)
                if(room[i].charAt(j) == 'P') {
                    boolean[][] visited = new boolean[N][N];
                    Queue<Node> queue = new LinkedList<>();

                    queue.add(new Node(i, j, 0));
                    visited[i][j] = true;

                    while (!queue.isEmpty()) {
                        Node current = queue.poll();

                        if(current.depth == 2)
                            continue;

                        for (int k = 0; k < 4; k++) {
                            int nx = current.x + dx[k], ny = current.y + dy[k];

                            if (inBound(nx, ny, room, visited)) {
                                if(room[nx].charAt(ny) == 'P')
                                    return 0;

                                else {
                                    queue.add(new Node(nx, ny, current.depth + 1));
                                    visited[nx][ny] = true;
                                }
                            }
                        }
                    }
                }

        return 1;
    }
}