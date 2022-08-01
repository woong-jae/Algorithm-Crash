class Solution {
    public long solution(int a, int b, int[] g, int[] s, int[] w, int[] t) {
        long max = (long) (1e9 * 2 * 1e5 * 2);
        long left = 0, right = max, T;
        int n = g.length, i;
        long answer = max;
        
        long gMax, sMax, gsMax;
        long gCur, sCur, wCur, tCur, gsCur;
        long roundCnt, wAll, ab = a + b;
        
        while (left <= right) {
            T = (left + right) / 2;
            gMax = sMax = gsMax = 0;
            
            for (i = 0; i < n; i++) {
                // 초기화
                gCur = g[i];
                sCur = s[i];
                wCur = w[i];
                tCur = t[i];
                gsCur = gCur + sCur;
                
                // 왕복 횟수
                roundCnt = (long) Math.ceil((double) (T / tCur) / 2);
                // 총 광물 무게 = 왕복 횟수 * 한 번에 운반 가능한 광물 무게
                wAll = roundCnt * wCur;
                
                // 각 도시에서 운반한 금의 무게
                gMax += Math.min(gCur, wAll);
                // 각 도시에서 운반한 은의 무게
                sMax += Math.min(sCur, wAll);
                // 각 도시에서 운반한 금과 은의 무게
                gsMax += Math.min(gsCur, wAll);
            }
            
            // 운반이 가능한 경우, 더 짧은 시간을 탐색
            if (a <= gMax && b <= sMax && ab <= gsMax) {
                right = T - 1;
                answer = T;
            } else left = T + 1;
        }
        
        return answer;
    }
}