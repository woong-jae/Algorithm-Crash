class Solution {
    public boolean solution(int[][] key, int[][] lock) {
        int m = key.length;
        int n = lock.length;

        for(int c = 0; c < 4; c++) {
            int[][] rotatedKey = rotate(m, c, key);

            for(int i = -m; i < n; i++)
                for(int j = -m; j < n; j++)
                    if(isValid(n, move(i, j, n, m, rotatedKey), lock))
                        return true;
        }
        return false;
    }

    private int[][] copy(int[][] origin, int m) {
        int[][] copied = new int[m][m];

        for(int i = 0; i < m; i++)
            for (int j = 0; j < m; j++)
                copied[i][j] = origin[i][j];

        return copied;
    }

    private int[][] rotate(int m, int count, int[][] origin) {

        if(count == 0)
            return origin;

        int[][] copied = copy(origin, m);
        int[][] result = null;

        for(int i = 0; i < count; i++) {
            result = new int[m][m];
            for(int j = 0; j < m; j++)
                for(int k = 0; k < m; k++)
                    result[k][m - 1 - j] = copied[j][k];
            copied = result;
        }
        return result;
    }

    private int[][] move(int i, int j, int n, int m, int[][] key) {
        int[][] moved = new int[n][n];

        for(int r = 0; r < n; r++)
            for(int c = 0; c < n; c++)
                if(inBound(r - i, c - j, m))
                    moved[r][c] = key[r - i][c - j];

        return moved;
    }

    private boolean inBound(int r, int c, int m) {
        return r >= 0 && c >= 0 && r < m && c < m;
    }

    private boolean isValid(int n, int[][] key, int[][] lock) {
        for(int i = 0; i < n; i++)
            for(int j = 0; j < n; j++)
                if(key[i][j] + lock[i][j] != 1)
                    return false;
        return true;
    }
}