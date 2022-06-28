
class Solution {
    static boolean[][] pillars;
    static boolean[][] covers;
    
    public int[][] solution(int n, int[][] build_frame) {
        pillars = new boolean[n + 1][n + 1];
        covers = new boolean[n + 1][n + 1];
        int count = 0;

        for (int[] bf : build_frame) {
            int x = bf[0];
            int y = bf[1];
            int struct = bf[2];
            int command = bf[3];

            // 기둥
            if (struct == 0) {
                // 삭제
                if (command == 0) {
                    pillars[x][y] = false;
                    if (!canDelete(n)) pillars[x][y] = true;
                    else count--;
                }
                // 설치
                else {
                    if (!checkPillar(x, y)) continue;
                    pillars[x][y] = true;
                    count++;
                }
            }
            // 보
            else {
                // 삭제
                if (command == 0) {
                    covers[x][y] = false;
                    if (!canDelete(n)) covers[x][y] = true;
                    else count--;
                }
                // 설치
                else {
                    if (!checkCover(x, y)) continue;
                    covers[x][y] = true;
                    count++;
                }
            }
        }

        int[][] answer = new int[count][3];
        int idx = 0;
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                if (pillars[i][j])
                    answer[idx++] = new int[]{i, j, 0};
                if (covers[i][j])
                    answer[idx++] = new int[]{i, j, 1};
            }
        }
        
        return answer;
    }
    
    private static boolean checkPillar(int x, int y) {
        // 바닥 설치
        if (y == 0) return true;
        // 아래에 기둥이 있는 경우
        else if (y > 0 && pillars[x][y - 1]) return true;
        // 한쪽에 보가 있는 경우
        else return x > 0 && covers[x - 1][y] || covers[x][y];
    }
    
    private static boolean checkCover(int x, int y) {
        // 한쪽 끝에 기둥이 있는 경우
        if (y > 0 && pillars[x][y - 1] || pillars[x + 1][y - 1]) return true;
        // 양쪽 끝이 모두 보와 연결된 경우
        else return x > 0 && covers[x - 1][y] && covers[x + 1][y];
    }

    private static boolean canDelete(int n) {
        for (int i = 0; i <= n; i++)
            for (int j = 0; j <= n; j++)
                // 기둥이 해당 위치에 있을 수 없거나, 보가 해당 위치에 있을 수 없는 경우 false
                if ((pillars[i][j] && !checkPillar(i, j)) || (covers[i][j] && !checkCover(i, j))) return false;
        return true;
    }
}