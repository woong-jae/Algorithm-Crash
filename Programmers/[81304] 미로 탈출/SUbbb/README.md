# [81304] 미로 탈출

## Algorithm
- 다익스트라
- 우선순위 큐
- 비트마스킹

## Logic

```java
class Node implements Comparable<Node> {
    int to;
    int weight;
    int status;

    public Node(int to, int weight, int status) {
        this.to = to;
        this.weight = weight;
        this.status = status;
    }

    // 오름차순 정렬을 위함, 여러 경로 중 최소 비용을 먼저 탐색하기 위함
    @Override
    public int compareTo(Node n) {
        return this.weight - n.weight;
    }
}
```

- 최소 비용을 구하기 위해 **우선순위 큐**를 사용하는데, 가중치를 기준으로 오름차순 정렬을 위해 `compareTo()` 를 오버라이딩한다.

```java
private void dijkstra(int s, int e) {
    queue = new PriorityQueue<>();
    dist[s][0] = 0;
    queue.add(new Node(s, 0, 0));

    while(!queue.isEmpty()) {
        Node node = queue.poll();
        int to = node.to;
        int weight = node.weight;
        int status = node.status;

        if (to == e) return;

        // 이동하려는 노드가 트랩인지 확인하고, 트랩이라면 이미 밟은 트랩인지 확인
        // f = 0, 밟지 않은 트랩이거나 일반 노드 / f = 1, 이미 밟은 트랩
        int f = 0;
        if (trapMap.containsKey(to)) {
            if ((status & trapMap.get(to)) != 0)
                f = 1;
        }

        forward(to, weight, status, f);
        backward(to, weight, status, f);
    }
}
```

- 다익스트라 알고리즘을 사용하여 트랩 여부에 따라 `f` 를 지정하고, 정방향 탐색과 역방향 탐색을 수행한다.

```java
private void forward(int to, int weight, int status, int f) {
    for (Node next : orgList[to]) {
        canForward = f;
        int nStatus = status;
        if (trapMap.containsKey(next.to)) {
            if ((status & trapMap.get(next.to)) != 0)
                canForward ^= 1;
            nStatus ^= trapMap.get(next.to);
        }

        if (canForward != 0) continue;
        if (dist[next.to][status] > weight + next.weight) {
            dist[next.to][status] = weight + next.weight;
            queue.add(new Node(next.to, dist[next.to][status], nStatus));
        }
    }
}
```

- 정방향 탐색의 경우, 
  - 다음 방문할 노드의 트랩 여부를 확인하고,  
  - 트랩인 경우, 트랩의 발동 여부를 확인한다.
  - `canForward` 가 `0` 인 경우(즉, 트랩이지만 활성화 되지 않았거나 일반 노드인 경우)만 `queue` 에 해당 지점을 추가한다.
  - 0인 경우: (f:canForward = 0:0 or 1:1)
- 역방향은 반대

## Review
- 시작지점부터 종료지점까지의 탐색을 위한 그래프 탐색 알고리즘과, 트랩을 방문한 경우 연결된 길을 `Switching` 해주는 알고리즘이 필요했다.
- 그래프 탐색은 DFS나 BFS를 사용할 수 있지만, 연결된 길에 대한 `Switching` 은 어떻게 해야할 지 감이 오지 않았다. 길의 비용은 고정적이지만, 방향만 바꿔줘야 했다.
- 참고 블로그를 보면서 이해하는데, "기본적인 로직은 최단 경로를 찾는 것이다. 또한 조건상 두 지점 간 여러 길이 존재할 수 있고, 길마다의 가중치가 있기에, 그래서 똑같은 경로라 하더라도 작은 가중치를 가진 간선을 위주로 탐색을 해야하므로 우선순위 큐를 사용한 다익스트라가 문제의 본질임을 알아낼 수 있다." 이러한 생각을 할 수 있도록 노력해야 겠다.
- 비트마스킹을 사용한 풀이를 참고했는데, 코드를 보고 봐도 이해가 어려운 것 같다. 자주 사용해보지 않은 알고리즘이이서 그런 것 같다. 비트마스킹을 사용하는 기본 예제를 풀어봐야겠다.

## 참고
[[프로그래머스] 2021 카카오 인턴 #4 미로 탈출 (Java)](https://loosie.tistory.com/341)