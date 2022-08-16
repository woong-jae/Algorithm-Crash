# [87694] 아이템 줍기

## Algorithm
- BFS

## Logic

```java
// 주어진 사각형 정보를 이용해 테두리와 내부 정보를 map에 표시
private void fillMap(int[] rect) {
    int x1 = rect[0] * 2;
    int y1 = rect[1] * 2;
    int x2 = rect[2] * 2;
    int y2 = rect[3] * 2;
    
    // 내부 색칠
    // -1 : 내부
    // 1 : 테두리
    for (int i = x1; i <= x2; i++) {
        for (int j = y1; j <= y2; j++) {
            if (map[i][j] == -1) continue;
            map[i][j] = -1;
            if (i == x1 || i == x2 || j == y1 || j == y2) map[i][j] = 1;
        }
    }       
}
```

- 주어진 사각형 좌표를 2배하여 저장한다. `ㄷ` 자형 만났을 때 점프하지 않도록 하기 위함이다...

```java
private void bfs(int cX, int cY, int iX, int iY) {
    int[][] dir = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
    boolean[][] visited = new boolean[101][101];
    Queue<Coor> queue = new LinkedList<>();
    queue.add(new Coor(cX, cY));
    
    while(!queue.isEmpty()) {
        Coor c = queue.poll();
        int curX = c.x;
        int curY = c.y;
        
        for (int[] d : dir) {
            int nX = curX + d[0];
            int nY = curY + d[1];
            
            if (!isValid(nX, nY)) continue;
            if (map[nX][nY] != 1 || visited[nX][nY]) continue;
            
            // map에 비용 저장
            map[nX][nY] = map[curX][curY] + 1;
            if (nX == iX && nY == iY) {
                // 이미 저장된 값이 있다면, 더 최소 값을 저장
                answer = Math.min(answer, map[nX][nY]);
                continue;
            }
            
            visited[nX][nY] = true;
            queue.add(new Coor(nX, nY));
        }
    }
}
```

- 그저 BFS, 다만 테두리인 녀석들만 골라 탐색한다. 아이템을 주워도 더 빨리 가는 방법이 있을 수 있으니 계속 탐색한다.

## Review
- 처음에는 큰 `map` 에 각 직사각형 좌표들을 추가하면서, 테두리는 `1` , 내부는 `-1` 로 표현하여 전체 테두리는 결국 `1` 로만 이루어지게 했다. 이후 `1` 인 좌표들을 따라가게 했는데, 반례로 인해 잘못됨을 알았다.
  - 사실 2배로 확장한다는 풀이를 얼핏 봤지만 아무리 생각해도 더 효율적으로 짤 수 있을 것 같아서 테두리마다의 방향을 저장하고 탐색 시에 사용하는 방법도 생각했지만 가만히 생각해보니 `ㄷ` 형태에서 무조건 목표대로 동작하지 않을 것 같았다.
- 그냥 졌다. 멍청이