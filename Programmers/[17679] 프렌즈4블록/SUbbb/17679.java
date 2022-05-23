
class Friends {
    char[][] blocks;
    boolean[][] check;
    int m;
    int n;

    public Friends(int m, int n, String[] board) {
        this.m = m;
        this.n = n;
        blocks = new char[m][n];
        initBlocks(board);
    }

    private void initBlocks(String[] board) {
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                blocks[i][j] = board[i].charAt(j);
    }

    public boolean checkBlocks() {
        check = new boolean[m][n];
        boolean has = false;

        for (int i = 0; i < m - 1; i++)
            for (int j = 0; j < n - 1; j++)
                if (blocks[i][j] != '-' && has4Blocks(i,j)) {
                    check[i][j] = true;
                    check[i][j + 1] = true;
                    check[i + 1][j] = true;
                    check[i + 1][j + 1] = true;
                    has = true;
                }

        return has;
    }

    private boolean has4Blocks(int x, int y) {
        return blocks[x][y] == blocks[x][y + 1] && blocks[x][y] == blocks[x + 1][y] && blocks[x][y] == blocks[x + 1][y + 1];
    }

    public int countBlocks() {
        int count = 0;
        
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++) {
                if (!check[i][j]) continue;
                count++;
                blocks[i][j] = '-';
            }
        
        return count;
    }

    public void dropBlocks() {
        for (int i = m - 1; i >= 0; i--)
            for (int j = 0; j < n; j++)
                if (blocks[i][j] == '-') pullBlocks(i, j);
    }

    private void pullBlocks(int x, int y) {
        for (int k = x - 1; k >= 0; k--) {
            if (blocks[k][y] != '-') {
                blocks[x][y] = blocks[k][y];
                blocks[k][y] = '-';
                break;
            }
        }
    }
}

class Solution {
    public int solution(int m, int n, String[] board) {
        Friends f = new Friends(m, n, board);

        int answer = 0;
        
        while (f.checkBlocks()) {
            answer += f.countBlocks();
            f.dropBlocks();
        }
        
        return answer;
    }
}