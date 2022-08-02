class Solution {
    private int[][] map;
    public int[] solution(int rows, int columns, int[][] queries) {
        int n = queries.length;
        int[] answer = new int[n];
        map = new int[rows][columns];

        for (int i = 1; i <= rows; i++)
            for (int j = 1; j <= columns; j++)
                map[i - 1][j - 1] = (i - 1) * columns + j;

        for (int i = 0; i < n; i++)
            answer[i] = rotation(queries[i][0] - 1, queries[i][1] - 1,
                    queries[i][2] - 1, queries[i][3] - 1);

        return answer;
    }

    private int rotation(int x1, int y1, int x2, int y2) {

        int min = map[x1][y1];
        int temp = map[x1][y1];

        // 좌변
        for (int x = x1; x < x2; x++) {
            min = Math.min(min, map[x + 1][y1]);
            map[x][y1] = map[x + 1][y1];
        }

        // 하변
        for (int y = y1; y < y2; y++) {
            min = Math.min(min, map[x2][y + 1]);
            map[x2][y] = map[x2][y + 1];
        }

        // 우변
        for (int x = x2; x > x1; x--) {
            min = Math.min(min, map[x - 1][y2]);
            map[x][y2] = map[x - 1][y2];
        }

        // 상변
        for (int y = y2; y > y1 + 1; y--) {
            min = Math.min(min, map[x1][y - 1]);
            map[x1][y] = map[x1][y - 1];
        }
        map[x1][y1 + 1] = temp;

        return min;
    }
}