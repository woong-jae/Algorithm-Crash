import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        int[][] cost = new int[N][3];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++)
                cost[i][j] = Integer.parseInt(st.nextToken());

            if (i == 0) continue;

            cost[i][0] = Math.min(cost[i][0] + cost[i - 1][1], cost[i][0] + cost[i - 1][2]);
            cost[i][1] = Math.min(cost[i][1] + cost[i - 1][0], cost[i][1] + cost[i - 1][2]);
            cost[i][2] = Math.min(cost[i][2] + cost[i - 1][0], cost[i][2] + cost[i - 1][1]);
        }

        System.out.println(Arrays.stream(cost[N - 1]).min().getAsInt());
    }
}