# [67260] 동굴 탐험
## Algorithm
- **DFS**
## Logic
- 먼저 방문해야 하는 방과 다음에 방문해야 할 방을 저장할 배열 2개를 만든다
- 방들을 양방향으로 트리에 저장하고 먼저 방문해야 하는 방들을 초기화
- 여기서 before[0] = 0 이 아니면 반드시 오류이므로 먼저 처리
- 0과 연결된 방들을 스택에 넣고
- 이미 방문한 방이면 `continue`
- 방문하지 않았지만 먼저 방문해야하는 방을 지나지 않았다면 
  - 먼저 방문해야 하는 방의 `after` 에 현재 방을 넣어줌
- 현재 방과 연관된 방들을 스택에 넣고, `현재 방 다음에 갈 방 (after)` 도 스택에 넣는다

```java
while (!stack.isEmpty()) {
    int current = stack.pop();

    if (visited[current])
        continue;

    if (!visited[before[current]]) {
        after[before[current]] = current;
        continue;
    }

    visited[current] = true;

    stack.addAll(tree[current]);
    stack.add(after[current]);
}
```

## Review
아이디어를 생각해내는데 오래걸린것 같다  
효율성 테스트가 있었기에 `DFS`를 활용한 완전 탐색을 하면 시간이 초과될 것 같아  
다른 방법을 자꾸 생각해보다가 일단 짜봤는데 30번 문제에서 계속 틀렸다  
그래서 찾아보니 테스트 케이스 30번에서는 `before[0]`에 0이 아닌게 들어가서 오류가 났는데  
0 이전에 다른 숫자가 오게되면 반드시 오류이기 때문에 미리 처리를 해주니까 통과했다    
이런 문제는 나중에 정신만 차리고 있으면 손은 댈수 있을지도...?
