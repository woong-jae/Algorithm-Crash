# [67260] 동굴 탐험

## Algorithm
- DFS, Stack

## Logic

```java
private void dfs() {
    Stack<Integer> stack = new Stack<>();
    stack.push(0);
    visited[0] = true;

    for (int next : adjList.get(0)) stack.push(next);
    
    while(!stack.empty()) {
        int now = stack.pop();
        if (visited[now]) continue;
        
        // before 정보를 업데이트하지 않는 방들은 모두 0번 방이 우선 방문되는 경우
        if (!visited[before[now]]) {
            after[before[now]] = now;
            continue;
        }
        
        visited[now] = true;
        
        for (int next : adjList.get(now))
            if (!visited[next]) stack.push(next);

        stack.push(after[now]);
    }
}
```

- Stack을 이용한 DFS를 수행한다.
- 0번 방에서 갈 수 있는 방들을 stack에 넣는다.
- 방문하지 않은 방에 대해, 
  - 우선 방문해야 하는 방을 방문하지 않은 경우, 이후 방문해야 하는 방들을 저장하는 `after` 에 현재 방을 저장한다.
  - 방문한 경우, 현재 방의 방문 여부를 최신화하고, 현재 방에서 갈 수 있는 방들을 stack에 넣는다.
- 이후 현재 방 이후에 방문해야 하는 방(`after[now]`)을 stack에 넣고, 반복한다.

## Review
- 위상 정렬 알고리즘을 떠올렸었는데, 방문했던 방도 다시 방문해야하는 경우가 있을 수 있어, 의미가 없다고 판단했다.
- 역시나 마지막 문제답게, 난이도가 높았다. 인턴 코테 5솔은 그림의 떡이라는 생각이 들었다 ..