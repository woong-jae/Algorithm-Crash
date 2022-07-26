import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public int solution(int m, int n, String[] board) {
        int answer = 0;
        char[][] b = new char[m][n];

        for(int i = 0; i < m; i++)
            for(int j = 0; j < n; j++)
                b[i][j] = board[i].charAt(j);

        while (true) {
            boolean check = false;
            boolean[][] remove = new boolean[m][n];

            for (int i = 0; i < m - 1; i++)
                for (int j = 0; j < n - 1; j++)
                    if(b[i][j] != '-' && isSquare(b, i, j)) {
                        remove[i][j] = true;
                        remove[i][j + 1] = true;
                        remove[i + 1][j] = true;
                        remove[i + 1][j + 1] = true;
                        check = true;
                    }

            if(!check)
                break;

            for (int i = 0; i < m; i++)
                for (int j = 0; j < n; j++)
                    if(remove[i][j]) {
                        b[i][j] = '-';
                        answer++;
                    }

            for (int j = 0; j < n; j++) {
                Queue<Character> queue = new LinkedList<>();

                for (int i = m - 1; i >= 0; i--)
                    if(b[i][j] != '-')
                        queue.add(b[i][j]);

                int index = m - 1;
                while (!queue.isEmpty())
                    b[index--][j] = queue.poll();

                for (int i = index; i >= 0; i--)
                    b[i][j] = '-';
            }
        }
        return answer;
    }

    private boolean isSquare(char[][] b, int x, int y) {
        return b[x][y + 1] == b[x][y] && b[x + 1][y] == b[x][y] && b[x + 1][y + 1] == b[x][y];
    }
}