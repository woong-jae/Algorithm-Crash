import java.io.*;
import java.util.*;

class Main {
    static long[][][] dp;
    static int mod = 1000000000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        // 100, 10, 1024
        dp = new long[N + 1][11][1<<10];

        for (int i = 1; i < 10; i++) dp[1][i][1 << i] = 1;

        for (int i = 2; i < N + 1; i++) {
            for (int end = 0; end < 10; end++) {
                for (int k = 0; k < 1024; k++) {
                    int b = k | (1 << end);

                    if (end == 0) {
                        dp[i][end][b] = (dp[i][end][b] + dp[i - 1][end + 1][k]) % mod;
                    } else if (end == 9) {
                        dp[i][end][b] = (dp[i][end][b] + dp[i - 1][end - 1][k]) % mod;
                    } else {
                        dp[i][end][b] = (dp[i][end][b] + dp[i - 1][end + 1][k] + dp[i - 1][end - 1][k]) % mod;
                    }
                }
            }
        }

        long answer = 0;
        for (int i = 0; i < 10; i++) {
            answer = (answer + dp[N][i][1023]) % mod;
        }

        System.out.println(answer);
    }
}