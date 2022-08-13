class Solution {
    public long solution(int n, int m, int x, int y, int[][] queries) {
        long rowS = x, rowE = x;
        long colS = y, colE = y;
        
        for (int i = queries.length - 1; i >= 0; i--) {
            int d = queries[i][0];
            int dx = queries[i][1];
            
            switch(d) {
                // 기존 0: 열이 감소하는 방향, 즉 열을 증가시켜야 함
                case 0: 
                    // 이미 끝이라면 갱신 X
                    if (colS != 0) colS += dx;
                    colE += dx;
                    
                    // 범위를 벗어나는 경우 제일 끝으로
                    if (colE > m - 1) colE = m - 1;
                    break;
                // 기존 1: 열이 증가하는 방향, 즉 열을 감소시켜야 함
                case 1:
                    colS -= dx;
                    // 범위를 벗어나는 경우 제일 끝으로
                    if(colS < 0) colS = 0;

                    // 이미 끝이라면 갱신 X
                    if(colE != m - 1) colE -= dx;
                    break;
                // 기존 2: 행이 감소하는 방향, 즉 행을 증가시켜야 함
                case 2:
                    // 이미 끝이라면 갱신 X
                    if (rowS != 0) rowS += dx;
                    rowE += dx;
                    
                    // 범위를 벗어나는 경우 제일 끝으로
                    if (rowE > n - 1) rowE = n - 1;
                    break;
                // 기존 3: 행이 증가하는 방향, 즉 행을 감소시켜야 함
                case 3:
                    rowS -= dx;
                    // 범위를 벗어나는 경우 제일 끝으로
                    if(rowS < 0) rowS = 0;

                    // 이미 끝이라면 갱신 X
                    if(rowE != n - 1) rowE -= dx;
                    break;
            }
            
            // 주어진 쿼리만으로 도착하지 못하는 경우는 바로 0을 반환
            if (rowS > n - 1 || rowE < 0 || colS > m - 1 || colE < 0) return 0;
        }
        
        // 행의 범위와 열의 범위를 곱한 것이 도달할 수 있는 공의 개수
        return (rowE - rowS + 1) * (colE - colS + 1);
    }
}