import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

class Main {
    static class Node {
        int vertex;
        int cost;

        public Node(int vertex, int cost) {
            this.vertex = vertex;
            this.cost = cost;
        }
    }
    private static final int INF = 1000000;
    private static int[] dist;
    private static List<Node>[] graph;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int TC = Integer.parseInt(br.readLine());

        for (int t = 0; t < TC; t++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int W = Integer.parseInt(st.nextToken());

            dist = new int[N + 1];
            graph = new List[N + 1];

            for (int i = 1; i <= N; i++)
                graph[i] = new ArrayList<>();

            for (int i = 0; i < M + W; i++) {
                st = new StringTokenizer(br.readLine());
                int S = Integer.parseInt(st.nextToken());
                int E = Integer.parseInt(st.nextToken());
                int T = Integer.parseInt(st.nextToken());

                if (i < M) {
                    graph[S].add(new Node(E, T));
                    graph[E].add(new Node(S, T));
                } else {
                    graph[S].add(new Node(E, -T));
                }
            }
            System.out.println(bellmanFord(N) ? "YES" : "NO");
        }
        br.close();
    }

    private static boolean bellmanFord(int N) {
        boolean check = false;
        Arrays.fill(dist, INF);
        dist[1] = 0;

        for (int i = 1; i < N; i++) {
            check = false;
            for (int j = 1; j <= N; j++) {
                for (Node next : graph[j]) {
                    if (dist[j] + next.cost < dist[next.vertex]) {
                        check = true;
                        dist[next.vertex] = dist[j] + next.cost;
                    }
                }
            }
            if (!check)
                return false;
        }

        return check;
    }
}