import java.util.*;

class Solution {
    class Edge {
        int index;
        int cost;

        public Edge(int index, int cost) {
            this.index = index;
            this.cost = cost;
        }
    }
    private final int INF = Integer.MAX_VALUE;
    public int solution(int N, int[][] road, int K) {
        List<Edge>[] graph = new List[N + 1];
        for (int i = 1; i <= N; i++)
            graph[i] = new ArrayList<>();

        for (int[] r : road) {
            graph[r[0]].add(new Edge(r[1], r[2]));
            graph[r[1]].add(new Edge(r[0], r[2]));
        }

        return (int) Arrays.stream(dijkstra(1, N, graph))
                .filter(i -> i <= K).count();
    }

    private int[] dijkstra(int start, int N, List<Edge>[] graph) {
        int[] costs = new int[N + 1];
        Queue<Edge> queue = new PriorityQueue<>(Comparator.comparingInt(e -> e.cost));

        Arrays.fill(costs, INF);
        costs[start] = 0;
        queue.add(new Edge(start, 0));

        while (!queue.isEmpty()) {
            Edge current = queue.poll();

            for (Edge next : graph[current.index]) {
                int cost = Math.min(costs[next.index], current.cost + next.cost);

                if (cost < costs[next.index]) {
                    costs[next.index] = cost;
                    queue.add(new Edge(next.index, cost));
                }
            }
        }
        return costs;
    }
}