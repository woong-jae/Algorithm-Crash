class Solution {
    static int[][] attackOrHeal;
    static int[][] board;
    static int rSize;
    static int cSize;
    
    public int solution(int[][] board, int[][] skill) {
        this.board = board;
        this.rSize = board.length;
        this.cSize = board[0].length;
        attackOrHeal = new int[rSize][cSize];
        
        for (int[] sk : skill) {
            boolean attack = sk[0] == 1;
            int r1 = sk[1];
            int c1 = sk[2];
            int r2 = sk[3];
            int c2 = sk[4];
            int deg = sk[5];

            if (attack) fill(r1, c1, r2, c2, -deg);
            else fill(r1, c1, r2, c2, deg);
        }
        
        accumulate();
        
        return countUndestroyedBuildings();
    }
    
    // 각 열별 오른쪽 누적합, 각 행별 아래쪽 누적합 계산
    private void accumulate() {
        for (int i = 0; i < rSize; i++)
            for (int j = 1; j < cSize; j++)
                attackOrHeal[i][j] += attackOrHeal[i][j - 1];
        
        for (int i = 1; i < rSize; i++)
            for (int j = 0; j < cSize; j++)
                attackOrHeal[i][j] += attackOrHeal[i - 1][j];
    }
    
    // 누적합 배열과 board를 계산하여 내구도가 0보다 큰 건물 카운트
    private int countUndestroyedBuildings() {
        int count = 0;
        
        for (int i = 0; i < rSize; i++) 
            for (int j = 0; j < cSize; j++) 
                if (board[i][j] + attackOrHeal[i][j] > 0) count++;
        
        return count;
    }
    
    // 시간 복잡도를 줄이기 위해 누적합을 사용하는데, 이를 위해 필요한 배열 범위의 값 채우기
    private void fill(int r1, int c1, int r2, int c2, int deg) {
        attackOrHeal[r1][c1] += deg;
        if (c2 + 1 < cSize) attackOrHeal[r1][c2 + 1] += -deg;
        if (r2 + 1 < rSize) attackOrHeal[r2 + 1][c1] += -deg;
        if (r2 + 1 < rSize && c2 + 1 < cSize) attackOrHeal[r2 + 1][c2 + 1] += deg;
    }
}