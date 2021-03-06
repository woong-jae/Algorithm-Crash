# [60063] 블록 이동하기

## Algorithm
- BFS
- 구현

## Logic

```java
private void moveRobot() {
    ...
}
```
- BFS 방식으로 `board` 탐색하는 함수로, 총 3번의 `for` 문이 존재한다.
  - 첫번째는 주어진 좌표로부터 이동 가능한 상하좌우 좌표를 queue에 추가
  - 두번째와 세번재는 로봇의 좌표 중 하나를 기준으로 90도 회전하여 갈 수 있는 좌표를 queue에 추가

## Review
- BFS를 사용해 목적지까지 최소 비용을 구하는 문제라 판단했다. 그래프 탐색 시에는 DFS, BFS가 가능한데, BFS가 최소 거리를 구하는데 더 효율적인 알고리즘이라는 것을 배웠던 기억이 나 BFS를 사용했다.
- 아이디어 자체는 쉽게 떠올렸다만, 로봇의 좌표가 2개인 점과 이에 대한 회전 구현에 있어 어려웠다 ..