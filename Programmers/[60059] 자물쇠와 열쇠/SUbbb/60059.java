import java.util.*;

class Solution {
    static int M;
    static int N;
    static int[][] bigMap;
    
    public boolean solution(int[][] key, int[][] lock) {  
        M = key.length;
        N = lock.length;
        
        for (int r = 0; r < M - 1 + N; r++) {
            for (int c = 0; c < M - 1 + N; c++) {
                for (int rotate = 0; rotate < 4; rotate++) {
                    // 자물쇠를 중앙에 두고, 키를 돌려가면서 확인할 큰 배열 선언
                    bigMap = new int[N + (M - 1) * 2][N + (M - 1) * 2];

                    // 자물쇠를 중앙에 배치
                    for (int i = 0; i < N; i++)
                        System.arraycopy(lock[i], 0, bigMap[M - 1 + i], M - 1, N);

                    // bigMap에 키를 더해 자물쇠에 맞는지 확인할 것
                    match(key, r, c);
                    if (canUnlock()) return true;
                }
            }
        }
        
        return false;
    }
    
    // bigMap이 1로만 이루어지는지 확인
    static private boolean canUnlock() {
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                if (bigMap[M - 1 + i][M - 1 + j] != 1) return false;
        return true;
    }

    // 키와 자물쇠를 더하는 함수
    static private void match(int[][] key, int r, int c) {
        key = rotatingKey(key);

        for (int i = 0; i < M; i++)
            for (int j = 0; j < M; j++)
                bigMap[i + r][j + c] += key[i][j];
    }
    
    // 키를 시계방향으로 90도 돌리는 함수
    static private int[][] rotatingKey(int[][] key) {
        for (int i = 0; i < M / 2; i++) {
            int[] tmp = Arrays.copyOf(key[M - i - 1], M);
            key[M - i - 1] = key[i];
            key[i] = tmp;
        }

        for (int i = 0; i < M; i++) {
            for (int j = i; j < M; j++) {
                int temp = key[i][j];
                key[i][j] = key[j][i];
                key[j][i] = temp;
            }
        }

        return key;
    }
}