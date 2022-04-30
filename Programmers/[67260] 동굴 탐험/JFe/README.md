# [67260] 동굴 탐험 - Python

## 🔍 Algorithm
**DFS**

## 💻 Logic

```Python
# tree 정보 입력
    for a, b in path:
        tree[a].append(b)
        tree[b].append(a)
# 방문 순서 정보 입력
    for a, b in order:
        # 0을 나중에 방문해야 하는 경우는 존재 X
        if b == 0: return False
        orders[b] = a
```
- `defaultdict`를 이용해 기본값이 **list**인 **dictionary** 생성하고 `path` 정보 저장  
- **방문 순서 정보 입력**  
  방문 순서 정보를 `orders` 리스트에 입력  
  이 때, **0**을 나중에 방문해야 하는 경우는 존재할 수 없기 때문에 `b`가 **0**이면 **False** 반환하고 종료  

```Python
# DFS 탐색
    stack.extend(tree[0])
    while stack:
        num = stack.pop()
        if visited[num]: continue
        # 먼저 방문해야 할 방이 있지만, 방문하지 않은 경우
        if orders[num] and not visited[orders[num]]:
            after[orders[num]] = num
            continue
        # 방문하지 않은 경우
        visited[num] = True
        stack.extend(tree[num])
        if after[num] != -1:
            stack.append(after[num])    # after 값이 있으면 append
# 하나라도 방문하지 않았으면 False
    if False in visited:
        return False
    return True
```
- **DFS 탐색**  
  **stack**을 이용해서 방문하지 않은 경우에 **stack**에 연결된 노드 값을 넣는 방식으로 **DFS** 탐색  
  먼저 방문해야 할 방이 있지만 방문하지 않은 경우에는 다음에 바로 방문해서 처리해주기 위해 먼저 방문해야 할 해당 `after` 값에 현재 값 저장  
  일반적인 경우에는 `visited` 업데이트 해주고, **stack**에 연결된 노드 값 **extend**하고, 해당 `after` 값이 있으면 그 값도 **append**  
  탐색 끝난 후에, 하나라도 방문하지 않은 곳이 있으면 **False** 반환  


## 📝 Review

문제를 이해하고 아이디어를 내기까지 시간이 오래걸렸다. DFS를 사용해야 한다는 것은 쉽게 알 수 있었지만, after 리스트를 만들어서 방문 우선순위를 처리하는 방법을 생각해내지 못해서 다른 사람들의 힌트를 보고 구현했다.. 그리고 30번 테스트 케이스도 계속 틀려서 이유를 몰랐는데 0을 나중에 방문해야 하는 경우는 존재할 수 없다는 것을 생각 못해서 나중에 이 부분을 처리하고 통과했다.  
카카오 인턴 코테 마지막 문제답게 쉽지 않다,,
