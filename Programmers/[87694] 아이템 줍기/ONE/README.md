# [87694] 아이템 줍기
## Algorithm
- **BFS**

## Logic
- 일반적으로 좌표 크기만큼 사각형을 그리고 테두리를 구하게 된다면 선이 뭉개지거나 제대로 된 그림이 그려지지 않는다
- 그래서 좌표의 크기들을 2배씩 확장하여 그림을 2배 확대해서 그린다면 제대로 그림을 그릴 수 있다  
- 먼저 rectangle에 있는 사각형들을 겹치게 좌표에 표시하고
- BFS를 이용하여 최단거리를 구하며 테두리를 그린다
- 구한 거리는 2배 확대된 거리이기 때문에 /2 해준다

```java
private void bfs(int cx, int cy, int ix, int iy) {
    Queue<Node> queue = new PriorityQueue<>(Comparator.comparingInt(n -> n.distance));

    queue.add(new Node(cx, cy, 0));
    outLineMap[cx][cy] = true;

    while (!queue.isEmpty()) {
        Node current = queue.poll();

        if (current.x == ix && current.y == iy) {
            answer = current.distance;
            return;
        }

        for (int d = 0; d < 4; d++) {
            int nx = current.x + dx[d];
            int ny = current.y + dy[d];
            if (inbound(nx, ny) && isOutLine(nx, ny) && !outLineMap[nx][ny]) {
                outLineMap[nx][ny] = true;
                queue.add(new Node(nx, ny, current.distance + 1));
            }
        }
    }
}
```

## Review
처음에는 그냥 그리고 테두리를 따라서 거리를 구했는데 선이 뭉개지거나 값이 제대로 구해지지 않았다  
그래서 해결법을 생각하다가 크기를 2배로 확대하면 될 것 같아서 해봤는데 바로 성공했다  
예시가 선이 뭉개지는 좋은 예시여서 문제를 금방 파악했지만 그런 예시가 아니었다면 오래걸렸을 것 같다  
쪼금 어려운 문제
