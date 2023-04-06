import java.util.*;
import java.util.stream.Collectors;

class Solution {
    static class Node {
        private final int to;
        private final int weight;

        public Node(int e, int w) {
            this.to = e;
            this.weight = w;
        }
    }

    private Set<Integer> gates;
    private Set<Integer> summits;
    private List<List<Node>> graph;

    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        graph = new ArrayList<>();
        for (int index = 0; index <= n; index++) {
            graph.add(new ArrayList<>());
        }

        this.gates = Arrays.stream(gates)
                .boxed()
                .collect(Collectors.toSet());
        this.summits = Arrays.stream(summits)
                .boxed()
                .collect(Collectors.toSet());

        for (int[] path : paths) {
            int from = path[0];
            int to = path[1];
            int weight = path[2];

            if (isGate(from) || isSummit(to)) {
                graph.get(from).add(new Node(to, weight));
            } else if (isGate(to) || isSummit(from)) {
                graph.get(to).add(new Node(from, weight));
            } else {
                graph.get(from).add(new Node(to, weight));
                graph.get(to).add(new Node(from, weight));
            }
        }
        return dijkstra(n);
    }

    private int[] dijkstra(int n) {
        Queue<Node> queue = new LinkedList<>();
        int[] intensity = new int[n + 1];

        Arrays.fill(intensity, Integer.MAX_VALUE);

        for (int gate : gates) {
            queue.add(new Node(gate, 0));
            intensity[gate] = 0;
        }

        while (!queue.isEmpty()) {
            Node current = queue.poll();
            if (current.weight > intensity[current.to]) {
                continue;
            }
            for (var next : graph.get(current.to)) {
                int max = Math.max(intensity[current.to], next.weight);
                if (intensity[next.to] > max) {
                    intensity[next.to] = max;
                    queue.add(new Node(next.to, max));
                }
            }
        }
        int minSummit = summits.stream()
                .min((a, b) -> intensity[a] == intensity[b] ? a - b : intensity[a] - intensity[b])
                .get();
        return new int[]{minSummit, intensity[minSummit]};
    }

    private boolean isGate(int num) {
        return gates.contains(num);
    }

    private boolean isSummit(int num) {
        return summits.contains(num);
    }
}