class Solution {
    private int minCount = Integer.MAX_VALUE;
    private int N;
    private int M;
    private boolean[] visitedRow;
    private boolean[] visitedColumn;
    private int[][] beginning;
    private int[][] target;

    public int solution(int[][] beginning, int[][] target) {
        this.N = beginning.length;
        this.M = beginning[0].length;
        this.visitedRow = new boolean[N];
        this.visitedColumn = new boolean[M];
        this.beginning = beginning;
        this.target = target;

        simulate(0);

        return minCount == Integer.MAX_VALUE ? -1 : minCount;
    }

    private void simulate(int count) {
        if (count > minCount) {
            return;
        }

        for (int row = 0; row < N; row++) {
            for (int col = 0; col < M; col++) {
                if (beginning[row][col] == target[row][col]) {
                    continue;
                }
                if (!visitedRow[row]) {
                    visitedRow[row] = true;
                    flipRow(row);

                    simulate(count + 1);

                    visitedRow[row] = false;
                    flipRow(row);
                }
                if (!visitedColumn[col]) {
                    visitedColumn[col] = true;
                    flipColumn(col);

                    simulate(count + 1);

                    visitedColumn[col] = false;
                    flipColumn(col);
                }
                return;
            }
        }
        minCount = count;
    }

    private void flipRow(int row) {
        for (int index = 0; index < M; index++) {
            beginning[row][index] = beginning[row][index] == 0 ? 1 : 0;
        }
    }

    private void flipColumn(int col) {
        for (int index = 0; index < N; index++) {
            beginning[index][col] = beginning[index][col] == 0 ? 1 : 0;
        }
    }
}