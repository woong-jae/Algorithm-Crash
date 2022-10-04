import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {
    static class Node {
        int vertex;
        int weight;

        public Node(int vertex, int weight) {
            this.vertex = vertex;
            this.weight = weight;
        }
    }
    private static int V;
    private static int dist, next;
    private static boolean[] visited;
    private static List<Node>[] graph;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        visited = new boolean[V + 1];
        graph = new List[V + 1];
        for (int i = 1; i <= V; i++)
            graph[i] = new ArrayList<>();

        for (int i = 1; i <= V; i++) {
            st = new StringTokenizer(br.readLine());
            int index = Integer.parseInt(st.nextToken());
            while (true) {
                int v = Integer.parseInt(st.nextToken());
                if (v == -1) break;
                graph[index].add(new Node(v, Integer.parseInt(st.nextToken())));
            }
        }

        visited[1] = true;
        dfs(1, 0);

        visited = new boolean[V + 1];
        visited[next] = true;
        dfs(next, 0);
        System.out.println(dist);
    }

    private static void dfs(int v, int sum) {
        if (dist < sum) {
            dist = sum;
            next = v;
        }

        for (Node node : graph[v]) {
            if (visited[node.vertex]) continue;
            visited[node.vertex] = true;
            dfs(node.vertex, sum + node.weight);
        }
    }
}