import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {
    private static  int N;
    private static final int INF = Integer.MAX_VALUE;
    private static int[][] cities;
    private static int[][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        cities = new int[N][N];
        dp = new int[N][(1 << N) - 1];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++)
                cities[i][j] = Integer.parseInt(st.nextToken());
        }
        br.close();

        Arrays.stream(dp).forEach(a -> Arrays.fill(a, INF));

        System.out.println(dfs(0, 1));
    }

    private static int dfs(int city, int bm) {
        // 모든 도시를 순회 했을 때
        if (bm == (1 << N) - 1) {
            if (cities[city][0] == 0)
                return INF;
            return cities[city][0];
        }

        // dp 값이 존재 하는 경우
        if (dp[city][bm] != INF)
            return dp[city][bm];

        for (int i = 0; i < N; i++)
            if ((bm & (1 << i)) == 0 && cities[city][i] != 0)
                // dfs(다음 도시, 다음도시 방문했다고 가정) + 여기서 다음 도시까지의 거리 와 최소거리 비교
                dp[city][bm] = Math.min(dp[city][bm], dfs(i, bm | (1 << i)) + cities[city][i]);

        return dp[city][bm];
    }
}