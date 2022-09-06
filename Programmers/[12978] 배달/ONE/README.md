# [12978] 배달
## Algorithm
- **Dijkstra**

## Logic
- 각 정점까지의 거리들로 인접 그래프를 만들고,
- 다익스트라를 이용해 1에서부터 각 정점까지의 최소거리를 구해 K 이하의 개수를 구한다

```java
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
```

## Review
다익스트라 기본문제