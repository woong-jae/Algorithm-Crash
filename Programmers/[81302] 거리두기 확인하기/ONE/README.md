# [81302] 거리두기 확인하기
## Algorithm
- **DFS**
## Logic
- 방한개씩 이중 반복문을 수행하며 'P'인 곳을 찾는다
- 'P'인 곳을 찾음면 깊이가 2일때 까지만 4방향 DFS 를 수행한다
- 만약 다음에 갈 수 있는 자리가 'P' 라면 바로 return 0 를 해서 정답 배열에 넣는다

```java
while (!queue.isEmpty()) {
    Node current = queue.poll();

    if(current.depth == 2)
        continue;

    for (int k = 0; k < 4; k++) {
        int nx = current.x + dx[k], ny = current.y + dy[k];

        if (inBound(nx, ny, room, visited)) {
            if(room[nx].charAt(ny) == 'P')
                return 0;

            else {
                queue.add(new Node(nx, ny, current.depth + 1));
                visited[nx][ny] = true;
            }
        }
    }
}
```

## Review
쉽게 풀수 있었던 문제
