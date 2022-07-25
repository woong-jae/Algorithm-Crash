# [92344] 파괴되지 않은 건물
## Algorithm
- **Cumulative Sum**

## Logic
- 해당 문제는 가로 X 세로의 최대가 100만이 되므로 일반적인 반복문을 통해 답을 구하면 효율성에서 시간 초과가 발생한다
- 2차원 배열에서 `누적합`을 이용하여 푸는 문제
- 주어지는 파괴와 회복이 모두 직사각형의 모양이기 때문에 가로와 세로로 누적합을 이용하여 풀이
  - (r1, c1) = n
  - (r1, c2+1) = -n
  - (r2+1, c1) = -n
  - (r2+1, c2+1) = n
- 위의 조건으로 누적합을 하게되면 (r1, c1)에서 (r2, c2)까지 직사각형 범위에 n을 더해줄 수 있다

```java
for (int[] s : skill) {
    int degree = s[0] == 1 ? -s[5] : s[5];
    int r1 = s[1];
    int c1 = s[2];
    int r2 = s[3];
    int c2 = s[4];

    cumulative[r1][c1] += degree;
    cumulative[r1][c2 + 1] -= degree;
    cumulative[r2 + 1][c1] -= degree;
    cumulative[r2 + 1][c2 + 1] += degree;
}

// 가로 누적합
for (int i = 0; i <= n; i++) {
    int sum = 0;
    for (int j = 0; j <= m; j++) {
        sum += cumulative[i][j];
        cumulative[i][j] = sum;
    }
}

// 세로 누적합
for (int j = 0; j < m; j++) {
    int sum = 0;
    for (int i = 0; i < n; i++) {
        sum += cumulative[i][j];
        cumulative[i][j] = sum;
    }
}
```

## Review
처음에는 좌표의 위치를 Map의 key값으로 저장하고 직사각형에 해당되는 좌표의 값을 빼거나 더하여 최종적으로 합을 구하는 방식으로 했는데  
역시나 시간초과가 나서 다른 사람의 풀이를 참고하여 누적합 문제라는 것을 알게 되었다  
누적합을 1차원 배열이 아닌 곳에서도 사용할 수 있다는 것을 알게 되었다  
빡센문제
