# [86052] 빛의 경로 사이클
## Algorithm
- **구현**

## Logic
- 타입고 네 방향의 boolean 을 저장하는 Node class 를 사용
- 각 노드들의 네 방향을 검사하며 방문한 적이 없는 방향이면 사이클의 시작점으로 두고 함수 호출
- 함수는 방향을 기반으로 다음 좌표를 구해 노드를 옮겨가고 해당 노드의 타입을 검사하여 다음 방향을 계산한다
- 만약 다음으로 갈 방향이 방문한적이 있다면 이미 사이클이 형성된 것이기 때문에 종료
- 그렇지 않다면 다음 방향의 노드로 옮겨가며 count 한다

```java    
private int getCount(int i, int j, int direction) {
    int count = 1;
    int x = i, y = j, d = direction;
    gridMap[i][j].direction[d] = true;

    while (true) {
        x = (x + dx[d]) == -1 ? row - 1 : (x + dx[d]) % row;
        y = (y + dy[d]) == -1 ? col - 1 : (y + dy[d]) % col;

        // 현재 노드의 타입으로 다음으로 갈 방향을 구한다
        d = gridMap[x][y].nextDirection(d);

        // 만약 지나간적이 있는 방향이라면 싸이클이 형성되었기 때문에 break
        if (gridMap[x][y].direction[d])
            break;

        gridMap[x][y].direction[d] = true;
        count++;
    }
    return count;
}
```

## Review
생각만하면 쉽게 풀 수 있는 문제
