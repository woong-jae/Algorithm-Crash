# [131703] 행렬과 연산
## Algorithm
- **DFS**

## Logic
- 직사각형의 최대 가로 세로 사이즈가 10이기 때문에 깊이 우선 탐색을 통해 여러가지 경우의 수를 탐색

```java
for (int row = 0; row < N; row++) {
    for (int col = 0; col < M; col++) {
        if (beginning[row][col] == target[row][col]) {
            continue;
        }
        if (!visitedRow[row]) {
            visitedRow[row] = true;
            flipRow(row);

            simulate(count + 1);

            visitedRow[row] = false;
            flipRow(row);
        }
        if (!visitedColumn[col]) {
            visitedColumn[col] = true;
            flipColumn(col);

            simulate(count + 1);

            visitedColumn[col] = false;
            flipColumn(col);
        }
        return;
    }
}
```

## Review
최대 10X10 이라서 완전 탐색으로 풀었는데 처음 방식은 새로운 배열을 계속 생성하는 방식이라 시간초과가 났다  
이후에는 기존 배열을 뒤집었다 복원하는 것으로 시간초과를 해결할 수 있었다!
