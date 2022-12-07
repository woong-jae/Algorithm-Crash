import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] color = new int[N][3];
        int[][] dp = new int[N][3];

        for (int index = 0; index < N; index++) {
            color[index] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        dp[0][0] = color[0][0];
        dp[0][1] = color[0][1];
        dp[0][2] = color[0][2];
        for (int index = 1; index < N; index++) {
            dp[index][0] = Math.min(dp[index - 1][1], dp[index - 1][2]) + color[index][0];
            dp[index][1] = Math.min(dp[index - 1][0], dp[index - 1][2]) + color[index][1];
            dp[index][2] = Math.min(dp[index - 1][0], dp[index - 1][1]) + color[index][2];
        }

        System.out.println(Math.min(Math.min(dp[N - 1][0], dp[N - 1][1]), dp[N - 1][2]));
    }
}