# [72413] 합승 택시 요금

## Algorithm
- 다익스트라

## Logic

```java
private static int[] dijkstra(int start) {
    int[] costs = new int[n + 1];
    Arrays.fill(costs, Integer.MAX_VALUE);

    Queue<Edge> queue = new PriorityQueue<>();
    queue.add(new Edge(start, 0));
    costs[start] = 0;

    while(!queue.isEmpty()) {
        Edge node = queue.poll();
        int now = node.dest;
        int weight = node.weight;

        // 현재 비용보다 더 큰 경우는 패스
        if (weight > costs[now]) continue;

        // 현재 정점에서 갈 수 있는 엣지 리스트
        ArrayList<Edge> edges = adjList[now];
        for (Edge e : edges) {
            // 현재(now) 정점까지의 비용과 다음으로 이동할 엣지의 비용의 합
            int cost = costs[now] + e.weight;

            // 이전 비용보다 새로운 비용이 더 작은 경우 갱신
            if (cost < costs[e.dest]) {
                costs[e.dest] = cost;
                queue.add(new Edge(e.dest, cost));
            }
        }
    }

    return costs;
}
```
- 다익스트라 알고리즘을 사용해 주어진 기점을 기준으로 각 지점까지의 최소 비용을 구하고, 이를 반환한다.

## Review
- source가 주어지고, 각 지점까지의 최소 비용을 구하는 **Single Source Shortest Path** 문제라는 것은 쉽게 알 수 있었고, 다익스트라 알고리즘을 사용했다.
- 다만, 합승 지점이라는 조건에 대해 막혔다. 
  - **`S -> X`, `X -> A`, `X -> B` 에서 X가 1 ~ N 지점이 가능**하다는 아이디어가 주요했다.