# [2026] 소풍 - Python

## 🔍 Algorithm
**DFS**

## 💻 Logic

```Python
# 인접 리스트 생성
for i in range(F):
    a, b = map(int, sys.stdin.readline().split())
    adj[a].append(b)
    adj[b].append(a)
# 인접 리스트 기준으로 DFS 탐색
for i in range(1, N + 1):
    visited = defaultdict(bool)
    visited[i] = True
    dfs(i, [i])
```
- **인접 리스트 생성 후, 인접 리스트 기준으로 DFS 탐색**  


```Python
def dfs(num, l):
    if len(l) == K:
        for v in l: print(v)
        exit()
    for i in range(num + 1, N + 1):
        if not visited[i]:
            for v in l:
                if v not in adj[i]: break
            else:
                visited[i] = True
                dfs(i, l+[i])
```
- **리스트 l에 있는 값이 인접 리스트(친구 관계)에 없으면 break**  
- **인접 리스트를 따라 계속 DFS 탐색**  
    K만큼의 길이가 되면 전부 출력하고 `exit()`


## 📝 Review

처음에 생각한대로 인접 리스트를 만들고, DFS 탐색하는 방향으로 구현했는데 계속 시간초과가 나서 다른 코드 좀 참고했다ㅠ