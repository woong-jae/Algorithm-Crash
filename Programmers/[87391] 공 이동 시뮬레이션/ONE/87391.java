class Solution {
    public long solution(int n, int m, int x, int y, int[][] queries) {
        int len = queries.length;
        long rowStart = x, rowEnd = x;
        long colStart = y, colEnd = y;

        for (int i = len - 1; i >= 0; i--) {
            int direction = queries[i][0];
            int distance = queries[i][1];

            switch (direction) {
                case 0: // 열을 증가
                    if (colStart != 0)
                        colStart += distance;
                    colEnd += distance;
                    if (colEnd > m - 1)
                        colEnd = m - 1;
                    break;
                case 1: // 열을 감소
                    colStart -= distance;
                    if (colStart < 0)
                        colStart = 0;
                    if (colEnd != m - 1)
                        colEnd -= distance;
                    break;
                case 2: // 행을 증가
                    if (rowStart != 0)
                        rowStart += distance;
                    rowEnd += distance;
                    if (rowEnd > n - 1)
                        rowEnd = n - 1;
                    break;
                case 3: // 행을 감소
                    rowStart -= distance;
                    if (rowStart < 0)
                        rowStart = 0;
                    if (rowEnd != n - 1)
                        rowEnd -= distance;
            }
            if (rowStart > n - 1 || rowEnd < 0 || colStart > m - 1 || colEnd < 0)
                return 0;
        }
        return (rowEnd - rowStart + 1) * (colEnd - colStart + 1);
    }
}