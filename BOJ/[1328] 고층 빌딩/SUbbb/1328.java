import java.io.*;
import java.util.*;

public class Main {
    static int N, L, R;
    static long[][][] dp;
    static final int MOD = 1000000007;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        dp = new long[101][101][101];

        dp[1][1][1] = 1;
        dp[2][2][1] = dp[2][1][2] = 1;

        for (int n = 3; n <= N; n++) {
            for (int l = 1; l <= L; l++) {
                for (int r = 1; r <= R; r++) {
                    dp[n][l][r] += dp[n - 1][l - 1][r] % MOD;
                    dp[n][l][r] += dp[n - 1][l][r - 1] % MOD;
                    dp[n][l][r] += (dp[n - 1][l][r] * (n - 2)) % MOD;
                    dp[n][l][r] %= MOD;
                }
            }
        }
        System.out.println(dp[N][L][R]);
    }
}