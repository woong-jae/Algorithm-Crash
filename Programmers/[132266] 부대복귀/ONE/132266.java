import java.util.*;

class Solution {
    static class Node {
        private final int from;
        private final int time;

        public Node(int from, int time) {
            this.from = from;
            this.time = time;
        }

        public int from() {
            return from;
        }

        public int time() {
            return time;
        }
    }

    private int[] times;
    private List<Integer>[] graph;

    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        times = new int[n + 1];
        Arrays.fill(times, -1);
        times[destination] = 0;

        graph = new List[n + 1];
        for (int[] road : roads) {
            if (graph[road[0]] == null) {
                graph[road[0]] = new ArrayList<>();
            }

            if (graph[road[1]] == null) {
                graph[road[1]] = new ArrayList<>();
            }

            graph[road[0]].add(road[1]);
            graph[road[1]].add(road[0]);
        }

        dijkstra(n, destination);

        return Arrays.stream(sources)
                .map(source -> times[source])
                .toArray();
    }

    private void dijkstra(int n, int destination) {
        Queue<Node> queue = new PriorityQueue<>(Comparator.comparingInt(Node::time));
        boolean[] visited = new boolean[n + 1];

        queue.add(new Node(destination, 0));
        visited[destination] = true;

        while (!queue.isEmpty()) {
            Node current = queue.poll();

            for (int to : graph[current.from()]) {
                if (visited[to]) {
                    continue;
                }
                visited[to] = true;
                times[to] = current.time() + 1;
                queue.add(new Node(to, current.time() + 1));
            }
        }
    }
}
