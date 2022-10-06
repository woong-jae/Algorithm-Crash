import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {
    private static final int INF = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        int[][] dist = new int[N + 1][N + 1];

        for (int[] d : dist)
            Arrays.fill(d, INF);
        for (int i = 1; i <= N; i++)
            dist[i][i] = 0;

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            if (dist[u][v] < w) continue;

            dist[u][v] = w;
            dist[v][u] = w;
        }

        st = new StringTokenizer(br.readLine()); br.close();
        int v1 = Integer.parseInt(st.nextToken());
        int v2 = Integer.parseInt(st.nextToken());

        for (int k = 1; k <= N; k++)
            for (int i = 1; i <= N; i++)
                for (int j = 1; j <= N; j++)
                    if (i != j && dist[i][k] != INF && dist[k][j] != INF)
                        dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);

        if (dist[v1][v2] == INF || dist[1][v1] == INF || dist[1][v2] == INF
                || dist[v1][N] == INF || dist[v2][N] == INF) {
            System.out.println(-1);
        } else {
            System.out.println(Math.min(dist[1][v1] + dist[v2][N] + dist[v1][v2],
                    dist[1][v2] + dist[v1][N] + dist[v1][v2]));
        }
    }
}