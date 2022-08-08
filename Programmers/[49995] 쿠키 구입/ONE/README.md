# [49995] 쿠키 구입
## Algorithm
- **DP**, **Two Pointer**

## Logic
- m의 인덱스를 0 ~ n-2 까지 바꿔가며 왼쪽, 오른쪽 방향으로 누적합들을 저장하는 DP 2차원 배열을 만든다
  - 왼쪽은 이전 DP 배열에서 cookie[m]을 더한값
  - 오른쪽은 이전 DP 배열에서 cookie[m]을 뺀 값
- 구한 DP 배열에서 1 ~ n-1(m = 0 ~ n-2) 행 반복문을 돌면서
  - start = 0, end = n - 1로 투포인터를 이용해서 양끝에서 부터 비교하면서 
    - 값이 같으면 answer의 값을 초기화해주고 반복문 종료 (양끝이 제일 큰값이기 때문에 최댓값이다)
    - start 인덱스의 값이 크면 start를 ++
    - end 인덱스의 값이 크면 end를 --
  - 위의 연산을 start가 m보다 작거나 같고 end가 m보다 클때까지 반복한다
    

```java
private int[][] init(int n, int[] cookie) {
    int[][] tmp = new int[n][n];

    tmp[0] = Arrays.copyOf(cookie, n);
    for (int i = 0; i < n - 1; i++)
        tmp[0][i + 1] += tmp[0][i];

    for (int m = 0; m < n - 1; m++) {
        tmp[m + 1][m] = cookie[m];
        // left
        for (int i = 0; i < m; i++)
            tmp[m + 1][i] = tmp[m][i] +  cookie[m];
        // right
        for (int i = m + 1; i < n; i++)
            tmp[m + 1][i] = tmp[m][i] - cookie[m];
    }
    return tmp;
}

private void compareSum() {
    for (int i = 1; i < n; i++) {
        int start = 0, end = n - 1, m = i - 1;

        while (start <= m && end > m) {
            if (dp[i][start] == dp[i][end]) {
                answer = Math.max(answer, dp[i][start]);
                break;
            } else if (dp[i][start] < dp[i][end]) {
                end--;
            } else {
                start++;
            }
        }
    }
}
```

## Review
처음에는 효율성을 통과하지 못해서 값을 비교하는 부분에서 시간을 줄일 방법을 생각했고  
투포인터가 생각나서 금방 해결할 수 있었다! 레벨 4치고는 쉬웠던 듯!
