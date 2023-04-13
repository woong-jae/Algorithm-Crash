class Solution {
    private final int MOD = 20170805;
    private final int STOP = 1;
    private final int ONE_WAY = 2;
    private int[][] cityMap;
    private int[][] caseCount;

    public int solution(int m, int n, int[][] cityMap) {
        this.cityMap = cityMap;
        caseCount = new int[m][n];
        caseCount[0][0] = 1;

        for (int row = 1; row < m; row++) {
            if (cityMap[row][0] != STOP) {
                caseCount[row][0] = caseCount[row - 1][0];
            }
        }

        for (int col = 1; col < n; col++) {
            if (cityMap[0][col] != STOP) {
                caseCount[0][col] = caseCount[0][col - 1];
            }
        }

        for (int row = 1; row < m; row++) {
            for (int col = 1; col < n; col++) {
                if (cityMap[row][col] == STOP) {
                    continue;
                }
                caseCount[row][col] = (getTopValue(row, col) + getLeftValue(row, col)) % MOD;
            }
        }

        return caseCount[m - 1][n - 1];
    }

    private int getTopValue(int x, int y) {
        if (cityMap[x - 1][y] == ONE_WAY) {
            return caseCount[x - 1][y] - caseCount[x - 1][y - 1];
        }
        return caseCount[x - 1][y];
    }

    private int getLeftValue(int x, int y) {
        if (cityMap[x][y - 1] == ONE_WAY) {
            return caseCount[x][y - 1] - caseCount[x - 1][y - 1];
        }
        return caseCount[x][y - 1];
    }
}
