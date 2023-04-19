# [1832] 보행자 천국
## Algorithm
- **DP**

## Logic

- **통과하지 못한 풀이지만 틀린 경우를 생각을 못한...**
- 현재 위치가 통행 불가면 0으로 표시
- 현재 위치가 통행 불가가 아닌 경우
  - 위 또는 왼쪽이 '2'인 경우 현재 위치에서의 대각선에 있는 값을 뺴준다
  - 대각선을 빼게 되면 **한방향**으로만 가는 경우의 값을 구할 수 있다

```java
for (int row = 1; row < m; row++) {
    for (int col = 1; col < n; col++) {
        if (cityMap[row][col] == STOP) {
            continue;
        }
        caseCount[row][col] = (getTopValue(row, col) + getLeftValue(row, col)) % MOD;
    }
}
```
```java
private int getTopValue(int x, int y) {
    if (cityMap[x - 1][y] == ONE_WAY) {
        return caseCount[x - 1][y] - caseCount[x - 1][y - 1];
    }
    return caseCount[x - 1][y];
}

private int getLeftValue(int x, int y) {
    if (cityMap[x][y - 1] == ONE_WAY) {
        return caseCount[x][y - 1] - caseCount[x - 1][y - 1];
    }
    return caseCount[x][y - 1];
}
```

## Review
위의 아이디어가 맞다고 생각하고 틀린 부분을 계속 찾았는데 다른 사람들의 풀이와도 차이점을 모르겠다  
일단 실패한 문제... 다른 사람의 코드를 참고해서 풀었다...
