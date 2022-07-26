import java.util.*;

class Solution {
    private int[][] weights;
    public int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = Integer.MAX_VALUE;
        weights = new int[n + 1][n + 1];

        for (int[] w : weights)
            Arrays.fill(w, 100001 * n);

        for (int i = 1; i <= n; i++)
            weights[i][i] = 0;

        for (int[] fare : fares) {
            int u = fare[0], v = fare[1], w = fare[2];
            weights[u][v] = w;
            weights[v][u] = w;
        }

        for (int k = 1; k <= n; k++)
            for (int i = 1; i <= n; i++)
                for (int j = 1; j <= n; j++)
                    if(weights[i][j] > weights[i][k] + weights[k][j])
                        weights[i][j] = weights[i][k] + weights[k][j];

        for (int i = 1; i <= n; i++)
            answer = Math.min(weights[s][i] + weights[i][a] + weights[i][b], answer);

        return answer;
    }
}