# [67259] 경주로 건설

## Algorithm
- **BFS**, **DP**, **Priority Queue**

## Logic
- 주요 아이디어는 한번의 `DFS`에 여러방향을 고려하는 것이아닌
- 4 방향 모두의 `DFS`의 최솟값을 찾아야한다
- 위를 구현하기 위해 2차원 배열에 방향까지 추가한 3차원 배열을 사용한다
- 큐가 빌 때까지 `while문`을 돌 것이 아니라, 
- 현재 경로의 가격을 기준으로 오름차순 정렬을 시키는 우선 순위 큐를 이용하면 
- 도착점을 만나자마자 바로 `while문`을 종료할 수 있기 때문에 우선 순위 큐를 이용
- 4방향을 검색하며 도로를 만들 수 있고 아직 방문하지 않은 방향이라면 배열의 값을 갱신하고 큐에 삽입

```java
while (!queue.isEmpty()) {
    Road cur = queue.poll();

    if (cur.row == N - 1 && cur.col == N - 1) {
        answer = cur.cost;
        break;
    }

    for (int d = 0; d < 4; d++) {
        int nrow = cur.row + dir[d][0];
        int ncol = cur.col + dir[d][1];
        int ncost = cur.cost + ((cur.dir == d) ? 100 : 600);

        if (canMake(nrow, ncol, board) && !alreadyVisit(nrow, ncol, ncost, d)) {
            visited[nrow][ncol][d] = ncost;
            queue.add(new Road(nrow, ncol, d, ncost));
        }
    }
}
```

## Review
처음에는 DFS 를 이용하여 풀었다가 4개가 시간초과가 났었고  
시간을 줄이는 법을 생각하다 BFS 를 이용한 방법을 떠올려 구현해봤으나 틀린답이 계속 나왔다  
혼자서 계속해보다가 안되가지고 결국 다른 사람의 코드를 참조해서 모든 방향의 경우의 수를 생각해야 된다는 것을 알게 되었다  
진짜 맵다.
