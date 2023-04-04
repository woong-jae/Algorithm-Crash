# [132266] 부대복귀
## Algorithm
- **Dijkstra**

## Logic
- 도착점에서 다른 지역까지의 최단 거리를 구해 각 소스에 대한 최소시간을 반환한 

```java
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
```

## Review
처음에는 각 소스에서 목적지로 가는 최단 시간을 계산 했는데 몇개의 케이스에서 시간 초과가 났다  
그래서 목적지에서 각 지역까지의 최단시간을 구하는 것으로 1번만 하면 되는 것을 알고 고쳐서 통과했다!  
먼가 카카오에서 비슷한 문제를 풀었던 것 같다
