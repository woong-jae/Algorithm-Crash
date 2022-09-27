import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int statusFullBit;
    static final int INF = 987654321;
    static int[][] w;
    static int[][] dp;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        // n까지 모두 방문함을 표현합니다.
        statusFullBit = (1<<n) -1;

        // 비용을 저장하는 배열을 초기화합니다.
        w = new int[n][n];
        dp = new int[n][statusFullBit];

        for (int i = 0; i < n; i++)
            Arrays.fill(dp[i], -1);

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++)
                w[i][j] = Integer.parseInt(st.nextToken());
        }

        // 0번 도시부터 탐색 시작
        // check: 0001 = 1
        // N은 최대 16 = 2^4 = 4자리 비트로 표현이 가능합니다.
        System.out.println(tsp(0,1));
    }

    static int tsp(int x, int check) {
        // 모든 도시 방문 완료한 경우
        if (check == statusFullBit) {
            // 그때의 x로부터 0으로 가는 비용이 0, 즉 갈 수 없는 경우인지 확인합니다.
            if (w[x][0] == 0) return INF;
            else return w[x][0];
        }

        // 이미 방문한 도시라면 그 도시까지의 비용을 반환합니다.
        if (dp[x][check] != -1) return dp[x][check];

        // 해당 도시에 방문했음을 표시
        // 두 번 방문하지 않도록 하기 위함입니다.
        dp[x][check] = INF;

        // 방문하지 않은 도시 탐색
        for (int i = 0; i < n; i++) {
            // i 도시 방문 표현
            int next = check | (1 << i);

            // 경로가 없거나 i 도시를 이미 방문했을 경우는 다음 도시 확인
            if (w[x][i] == 0 || (check & (1 << i)) != 0) continue;

            dp[x][check] = Math.min(dp[x][check], tsp(i, next) + w[x][i]);
        }

        return dp[x][check];
    }
}