# [1726] 로봇
## Algorithm
- **BFS**

## Logic
- 현재의 위치, 방향 정보와 이동 수를 저장하는 Node class 생성
- 방문기록을 위치에다가 방향까지 하여 BFS 수행
  - 좌, 우 방향과 직진을 1~3 까지를 확인하여 수행
  - 직진일 때는 만약 2칸에서 못간다면 3칸은 검사도 하지 않도록 함 

```java
while (!queue.isEmpty()) {
    Node current = queue.poll();

    if (current.isSame(dest)) {
        System.out.println(current.count);
        return;
    }

    if (!visited[current.x][current.y][turnRight(current.dir)]) {
        visited[current.x][current.y][turnRight(current.dir)] = true;
        queue.add(new Node(current.x, current.y, turnRight(current.dir),
                current.count + 1));
    }
    if (!visited[current.x][current.y][turnLeft(current.dir)]) {
        visited[current.x][current.y][turnLeft(current.dir)] = true;
        queue.add(new Node(current.x, current.y, turnLeft(current.dir),
                current.count + 1));
    }

    for (int k = 1; k <= 3; k++) {
        int nx = current.x + dx[current.dir] * k;
        int ny = current.y + dy[current.dir] * k;

        if (inbound(N, M, nx, ny) && !visited[nx][ny][current.dir]) {
            if (map[nx][ny] == 0) {
                visited[nx][ny][current.dir] = true;
                queue.add(new Node(nx, ny, current.dir, current.count + 1));
            }
            else
                break;
        }
    }
```

## Review
그냥 저냥 했던 문제