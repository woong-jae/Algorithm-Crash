# [12908] 텔레포트 3
## Algorithm
- **Backtracking**

## Logic
- 시작 위치와 목적지를 두고 텔레포트를 저장할 Teleport class 생성
  - 텔레포트는 3가지 경우의 수가 있다
    - 앞쪽으로 텔을 타서 끝으로 나올 때
    - 뒤쪽으로 텔을 타서 앞으로 나올 때
    - 텔을 안탈 때
- 위의 3가지 경우를 생각하여 백트래킹
  - 텔을 탈 때에는 현재 위치에서 텔까지의 거리와 텔을 타는 10초의 시간을 더하고 다음 지점을 텔의 반대 쪽으로 한다
  - 텔을 안탈 때에는 그냥 현재 위치에 고정해둔다 

```java
private static void dfs(int depth, long time, Node current, Node dest, boolean[] visited) {
    if (depth == 3) {
        min = Math.min(min, time + current.getTime(dest));
        return;
    }
    for (int i = 0; i < 3; i++)
        if (!visited[i]) {
            Teleport teleport = teleports.get(i);
            visited[i] = true;
            // 앞쪽으로 텔 탈 때
            dfs(depth + 1, time + current.getTime(teleport.start) + 10,
                    new Node(teleport.end.x, teleport.end.y), dest, visited);
            // 뒤쪽으로 텔 탈 때
            dfs(depth + 1, time + current.getTime(teleport.end) + 10,
                    new Node(teleport.start.x, teleport.start.y), dest, visited);
            // 텔 안탈 때
            dfs(depth + 1, time, new Node(current.x, current.y), dest, visited);
            visited[i] = false;
        }
}
```

## Review
처음에는 BFS를 사용해 푸는 것으로 접근 했으나 한 좌표의 크기가 10억으로 visit 배열을 사용하려고 하면 메모리초과가 났기에  
텔레포트의 개수와 위치가 고정돼있다는 것을 생각하여 백트래킹을 사용한 것으로 접근했고 예시는 통과 했었다  
그러나 자꾸 채점하자마자 틀려서 이건 답이 int가 아니라 long인가? 라고 생각해 long으로 바꾸니 통과했다  
백준...
