class Solution {
    static int n;
    static int[] info;
    static int[] ryan;
    static int[] answer = new int[]{ -1 };
    static int max = 0;
    
    public int[] solution(int n, int[] info) {
        this.n = n;
        this.info = info;
        this.ryan = new int[11];
        
        dfs(1);
        
        return answer;
    }
    
    private static void dfs(int cnt) {
        if (cnt == n + 1) {
            // 점수 계산
            int ryanScore = 0;
            int apeachScore = 0;
            
            for (int i = 0; i <= 10; i++) {
                if (info[i] == 0 && ryan[i] == 0) continue;
                
                if (info[i] < ryan[i]) ryanScore += 10 - i;
                else apeachScore += 10 - i;
            }
            
            if (ryanScore > apeachScore && ryanScore - apeachScore >= max) {
                max = ryanScore - apeachScore;
                answer = ryan.clone();
            }
            
            return;
        }
        // ryan이 쏜 화살이 info보다 크면 진행할 필요 없음
        for (int i = 0; i <= 10 && ryan[i] <= info[i]; i++) {
            ryan[i]++;
            dfs(cnt + 1);
            ryan[i]--;
        }
    }
}