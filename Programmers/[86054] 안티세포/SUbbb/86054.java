import java.util.*;

class Solution {
    public int[] solution(int[] a, int[] s) {
        int size = s.length;
        
        int[] answer = new int[size];
        int idx = 0;
        int i = 0;
        
        for (int len : s) {
            int[] b = Arrays.copyOfRange(a, i, i + len);
            answer[idx++] = anti(b);
        }
        
        return answer;
    }
    
    private int anti(int[] b) {
        int n = b.length;
        // int e = ;
        // int[][] dp = new int[n][e];
        int[] prefixSum = new int[n];
        
        // prefixSum
        prefixSum[0] = b[0];
        for (int i = 1; i < n; i++)
            prefixSum[i] = b[i] + prefixSum[i - 1];
        
        // dp 배열 초기화
        dp[0][0] = 0;
        // for (int i = 0; i < e; i++)
        //     dp[0][i] = 0;
        
        while(i != n) {
            int X = b[i];
            
        }
    }
}