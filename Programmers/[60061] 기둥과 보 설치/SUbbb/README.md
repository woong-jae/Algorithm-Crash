# [60061] 기둥과 보 설치

## Algorithm
- 구현

## Logic

```java
private static boolean checkPillar(int x, int y) {
  // 바닥 설치
  if (y == 0) return true;
  // 아래에 기둥이 있는 경우
  else if (y > 0 && pillars[x][y - 1]) return true;
  // 한쪽에 보가 있는 경우
  else return x > 0 && covers[x - 1][y] || covers[x][y];
}

private static boolean checkCover(int x, int y) {
  // 한쪽 끝에 기둥이 있는 경우
  if (y > 0 && pillars[x][y - 1] || pillars[x + 1][y - 1]) return true;
  // 양쪽 끝이 모두 보와 연결된 경우
  else return x > 0 && covers[x - 1][y] && covers[x + 1][y];
}

private static boolean canDelete(int n) {
  for (int i = 0; i <= n; i++)
      for (int j = 0; j <= n; j++)
          // 기둥이 해당 위치에 있을 수 없거나, 보가 해당 위치에 있을 수 없는 경우 false
          if ((pillars[i][j] && !checkPillar(i, j)) || (covers[i][j] && !checkCover(i, j))) return false;
  return true;
}
```
- `checkPillar` 와 `checkCover` 는 각각 기둥과 보가 설치가능한지를 확인하는 함수이다.
  - 주어진 조건에 따라 이를 확인한다.
- `canDelete` 는 기둥과 보를 주어진 명령대로 삭제했을 때, 주어진 조건을 불만족하는 좌표가 없는지 확인한다.

## Review
- 처음 구현 시, 기둥과 보의 삭제 조건 확인때문에 애를 먹었고, `map` 하나에 기둥 여부와 보 여부를 함께 저장했었는데, 생각해보니 기둥과 보가 한 좌표에 함께 존재할 수 있었던 것을 간과했었다.
- 또한, 설치와 삭제하는 구조물에 대한 `ArrayList` 를 만들어서 관리할 구조물의 범위를 줄이려 했는데 구현에서 조금 막혔다 ...
  - 새 객체를 만들어서 이미 있는 `Map` 에서 값을 조회하려하니 아예 다른 객체로 판단을 해서 조회가 불가능했다.