# [67260] 동굴 탐험
## Algorithm
- DFS
- Set, Map
## Logic
선행되는 방을 모두 방문하게 되면 규칙에 맞게 방을 모두 방문할 수 있다는게 기본 아이디어다.

첫 구현은 DFS로 각 방마다 0으로부터 경로를 `Map`에 저장한 후, 먼저 방문해야 하는 방을 방문할 수 있는지 확인하는 방식이였다.
정확성은 다 맞았지만, 효율성은 모두 시간초과가 났다.

그래서 DFS를 하는 와중에 선행되는 방을 모두 방문했는지 확인하는 방식으로 바꿨다.

만약 뒤에 방문해야 하는 방을 만났다면, `pending`에 두어 나중에 처리할 수 있을 때까지 저장해놓는다.
그렇게 탐색하다가 만약 선행되는 방을 찾았고, 선행되는 방 다음 방문해야 하는 방이 `pending`에 있으면 스택에 넣어주는 식으로 처리했다.

```js
while(stack.length) {
    const current = stack.pop();
    adjList[current].forEach(next => {
        if(visited[next]) return;
        // 아직 선행되는 방을 방문하지 않음
        if(followings.has(next)) {
            pending.add(next);
            return;
        }
        // 선행되는 방이면 후행되는 방을 제거하고, 대기중인 방이 있다면 스택에 넣어줌
        if(priors.has(next)) {
            const following = priors.get(next);
            followings.delete(following);
            priors.delete(next);
            if(pending.has(following)) {
                stack.push(following);
                visited[following] = true;
                pending.delete(following);
            }
        }
        
        stack.push(next);
        visited[next] = true;
    });
    // 선행되는 방이 모두 처리되었다면 모두 방문 가능함
    if(!priors.size) return true;
}

return false;
```

## Review
아이디어는 빨리 잡았는데 구현이 너무 느렸다. 노력하자...