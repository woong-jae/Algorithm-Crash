import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
    private static final int MOD = 1000000000;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long answer = 0;
        int N = Integer.parseInt(br.readLine());
        br.close();
        long[][][] dp = new long[N + 1][10][1<<10];

        for (int i = 1; i < 10; i++)
            dp[1][i][1 << i] = 1;

        for (int i = 2; i <= N; i++) {
            for (int end = 0; end <= 9; end++) {
                for (int k = 0; k < 1024; k++) {
                    int n = k | (1 << end);

                    if (end == 0) {
                        dp[i][end][n] = (dp[i][end][n] + dp[i - 1][end + 1][k]) % MOD;
                    } else if (end == 9) {
                        dp[i][end][n] = (dp[i][end][n] + dp[i - 1][end - 1][k]) % MOD;
                    } else {
                        dp[i][end][n] = (dp[i][end][n] + dp[i - 1][end + 1][k] + dp[i - 1][end - 1][k]) % MOD;
                    }
                }
            }
        }

        for (int i = 0; i < 10; i++)
            answer = (answer + dp[N][i][1023]) % MOD;

        System.out.println(answer);
    }
}