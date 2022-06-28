class Solution {

    private boolean[][] pillar;
    private boolean[][] bar;

    public int[][] solution(int n, int[][] build_frame) {
        pillar = new boolean[n + 1][n + 1];
        bar = new boolean[n + 1][n + 1];

        int count = 0;
        for (int[] frame : build_frame) {
            int x = frame[0];
            int y = frame[1];
            int type = frame[2];
            int action = frame[3];

            if (type == 0) { // 기둥
                if (action == 1) { // 설치
                    if (checkPillar(x, y)) {
                        pillar[x][y] = true;
                        count++;
                    }
                }
                else { // 삭제
                    pillar[x][y] = false;
                    if (!canDelete(n))
                        pillar[x][y] = true;
                    else count--;
                }
            } else { // 보
                if (action == 1) {
                    if (checkBar(x, y)) { // 설치
                        bar[x][y] = true;
                        count++;
                    }
                }
                else { // 삭제
                    bar[x][y] = false;
                    if (!canDelete(n))
                        bar[x][y] = true;
                    else count--;
                }
            }
        }

        int[][] result = new int[count][3];
        int idx = 0;

        for(int i = 0; i <= n; i++) {
            for(int j = 0; j <= n; j++) {
                if(pillar[i][j]) {
                    result[idx][0] = i;
                    result[idx][1] = j;
                    result[idx++][2] = 0;
                }
                if(bar[i][j]) {
                    result[idx][0] = i;
                    result[idx][1] = j;
                    result[idx++][2] = 1;
                }
            }
        }
        return result;
    }

    public boolean canDelete(int n) {
        for(int i = 0; i <= n; i++) {
            for(int j = 0; j <= n; j++) {
                if(pillar[i][j] && !checkPillar(i, j))  return false; // 기둥이 해당 위치에 있을 수 없다면 false
                else if(bar[i][j] && !checkBar(i, j)) return false; // 보가 해당 위치에 있을 수 없다면 false
            }
        }
        return true;
    }

    public boolean checkPillar(int x, int y) {
        if(y == 0) return true; // 바닥에 설치하는 경우
        else if(y > 0 && pillar[x][y - 1]) return true; // 아래 기둥이 있는 경우
        else return x > 0 && bar[x - 1][y] || bar[x][y];
    }

    public boolean checkBar(int x, int y) {
        if(y > 0 && pillar[x][y - 1] || pillar[x + 1][y - 1]) return true; // 한쪽 끝에 기둥이 있는 경우
        else return x > 0 && bar[x - 1][y] && bar[x + 1][y]; // 양쪽 끝이 보와 동시에 연결되어 있는 경우
    }
}