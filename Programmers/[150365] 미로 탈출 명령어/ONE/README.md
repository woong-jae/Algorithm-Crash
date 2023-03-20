# [150365] 미로 탈출 명령어
## Algorithm
- **DFS**

## Logic
- DFS를 활용한 탐색으로 경로를 구한다
  - 먼저 탐색을 시작하기 전, 절대 k 길의의 경로로 도달 못하는 경우의 수를 제외한다
    ```java
      private boolean canArrive(Position position, int k) {
          int minDistance = position.getDistance(goal);
          return minDistance <= k && (k - minDistance) % 2 != 1;
      }
    ```
  - 현재 위치에서 목적지까지의 최단거리가 k 보다 크면 도달할 수 없다
  - 현재 위치에서 목적지까지의 최단 거리와 k의 차이가 홀수 이면 도달할 수 없다(짝수 일 때는 특정 경로에 대한 반복이 가능하다)
- 이후 경로에 대한 정보는 k 길이로 제한되어 있기 때문에 char 배열에 depth 마다 경로 정보를 저장하고, 사전순으로 탐색하기 때문에 가장 먼저 도달한 것이 사전 순으로 가장 빠르다는 것을 보장한다
    ```java
    private void findRoute(Position current, int depth, int k) {
        if (!answer.isEmpty()) {
            return;
        }
    
        if (depth == k) {
            if (current.reach(goal)) {
                answer = new String(route);
            }
            return;
        }
    
        for (Direction direction : Direction.values()) {
            int nx = current.x() + direction.dx;
            int ny = current.y() + direction.dy;
    
            if (!maze.isInbound(nx, ny)) {
                continue;
            }
            Position next = new Position(nx, ny);
            if (!canArrive(next, k - depth - 1)) {
                continue;
            }
            route[depth] = direction.initial;
            findRoute(next, depth + 1, k);
        }
    }
    ```

## Review
처음엔 일반적인 DFS로 접근했다가 시간초과가 발생해서 가지치기 할 수 있는 방법에 대해 고민했고,  
도달할 수 없는 경우에 대해 생각해낼 수 있어서 시간을 줄일 수 있었던 것 같다!  
그리고 처음엔 정답에 대한 경로를 문자열 연산을 사용하는 것을 사용했지만 이보다 경로수가 정해져 있기 때문에,  
k 크기의 문자배열을 사용해 메모리 사용량도 줄일 수 있었다고 생각한다! 조금 어려웠던 문제!