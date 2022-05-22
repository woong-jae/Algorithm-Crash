# [17679] 프렌즈4블록
## Algorithm
- **구현**
## Logic
- 2차원 배열을 순환하면서 사각형이 같은 알파벳 4개로 만들어지는지 확인
- 만약에 만들어진다면 boolean 2차원 배열에 true로 바꿔주고 
  - 배열을 다 돈 뒤, boolean 배열에서 true로 바뀐값들을 '-' 로 바꾼다
  - 바꾼 '-'가 있는 열들을 세로로 내려준다
- 더이상 같은 알파벳 4개로 이루어지는 사각형이 생기지 않을 때까지 위를 반복

```java
while (true) {
    boolean check = false;
    boolean[][] remove = new boolean[m][n];

    for (int i = 0; i < m - 1; i++)
        for (int j = 0; j < n - 1; j++)
            if(b[i][j] != '-' && isSquare(b, i, j)) {
                remove[i][j] = true;
                remove[i][j + 1] = true;
                remove[i + 1][j] = true;
                remove[i + 1][j + 1] = true;
                check = true;
            }

    if(!check)
        break;

    for (int i = 0; i < m; i++)
        for (int j = 0; j < n; j++)
            if(remove[i][j]) {
                b[i][j] = '-';
                answer++;
            }

    for (int j = 0; j < n; j++) {
        Queue<Character> queue = new LinkedList<>();

        for (int i = m - 1; i >= 0; i--)
            if(b[i][j] != '-')
                queue.add(b[i][j]);

        int index = m - 1;
        while (!queue.isEmpty())
            b[index--][j] = queue.poll();

        for (int i = index; i >= 0; i--)
            b[i][j] = '-';
    }
}
```

## Review
그냥 시키는대로 구현하면 됐던 쉬운 문제
