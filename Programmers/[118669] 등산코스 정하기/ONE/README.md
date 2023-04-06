# [118669] 등산코스 정하기
## Algorithm
- **Dijkstra**

## Logic
- 최대 intensity 를 단방향으로 구하면 왕복으로 구하면 되기 때문에 한방향으로만 구해서 답을 도출하면 된다
- 가중치의 도로의 방향성은 크게 3가지로 나뉜다
  1. 출발점이 게이트거나 도착지가 봉우리일 경우 단방향
  2. 도착지가 게이트거나 출발점이 봉우리일 경우 단방향
  3. 이외에 가중치는 모두 양방향
- 해당 가중치 그래프로 다익스트라 알고리즘을 수행하는데, intensity에 대한 갱신은 다음과 같은 경우에 진행한다
  - intensity[next.to] > Math.max(intensity[current.to], next.weight)
  - 다음으로 갈곳의 intensity 가 현재까지의 intensity, 가중치 중 큰 값보다 클 때 갱신된다
- 이 후 우선 순위에 따른 봉우리 Number 와 intensity 를 배열로 반환한다
```java
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
```

## Review
처음에는 일반적인 다익스트라로 구현했으나 시간초과가 발생했고 카카오 테크 블로그의 풀이를 참고했다  
역시나 생각이 필요한 문제 였고 Intensity 를 갱신하는 부분에서는 비슷한 유형의 문제를 만나게 되면 도움이 될 것 같다고 생각했다  
어려운 문제!
