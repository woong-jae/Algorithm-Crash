import java.util.ArrayList;
import java.util.List;

class Solution {
    class Node {
        char type;
        boolean[] direction;

        public Node(char type) {
            this.type = type;
            this.direction = new boolean[4];
        }

        public int nextDirection(int d) {
            switch (this.type) {
                case 'S':
                    return d;
                case 'L':
                    return d == 0 ? 3 : d - 1;
                case 'R':
                    return (d + 1) % 4;
                default:
                    return -1;
            }
        }
    }
    private int row, col;
    // 0: up, 1: right, 2: down, 3: left
    private final int[] dx = {-1, 0, 1, 0};
    private final int[] dy = {0, 1, 0, -1};
    private Node[][] gridMap;
    public int[] solution(String[] grid) {
        List<Integer> answer = new ArrayList<>();
        this.row = grid.length;
        this.col = grid[0].length();
        this.gridMap = new Node[row][col];

        for (int i = 0; i < row; i++)
            for (int j = 0; j < col; j++)
                gridMap[i][j] = new Node(grid[i].charAt(j));

        // 모든 노드의 모든 방향을 방문했는지 확인
        for (int i = 0; i < row; i++)
            for (int j = 0; j < col; j++)
                for (int d = 0; d < 4; d++)
                    if (!gridMap[i][j].direction[d])
                        answer.add(getCount(i, j, d));

        return answer.stream().sorted().mapToInt(Integer::intValue).toArray();
    }

    private int getCount(int i, int j, int direction) {
        int count = 1;
        int x = i, y = j, d = direction;
        gridMap[i][j].direction[d] = true;

        while (true) {
            x = (x + dx[d]) == -1 ? row - 1 : (x + dx[d]) % row;
            y = (y + dy[d]) == -1 ? col - 1 : (y + dy[d]) % col;

            // 현재 노드의 타입으로 다음으로 갈 방향을 구한다
            d = gridMap[x][y].nextDirection(d);

            // 만약 지나간적이 있는 방향이라면 싸이클이 형성되었기 때문에 break
            if (gridMap[x][y].direction[d])
                break;

            gridMap[x][y].direction[d] = true;
            count++;
        }
        return count;
    }
}