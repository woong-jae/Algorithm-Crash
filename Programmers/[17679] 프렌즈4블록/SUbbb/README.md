# [17679] 프렌즈4블록

## Algorithm
- 구현

## Logic

```java
while (f.checkBlocks()) {
    answer += f.countBlocks();
    f.dropBlocks();
}
```

- 흐름은,
  - 보드에 4블록이 있는지 확인하고,
  - 있다면 없앨 수 있는 블록 수를 계산하고,
  - 보드를 최신화한다.

```java
public boolean checkBlocks() {
    check = new boolean[m][n];
    boolean has = false;

    for (int i = 0; i < m - 1; i++)
        for (int j = 0; j < n - 1; j++)
            if (blocks[i][j] != '-' && has4Blocks(i,j)) {
                check[i][j] = true;
                check[i][j + 1] = true;
                check[i + 1][j] = true;
                check[i + 1][j + 1] = true;
                has = true;
            }

    return has;
}
```
- 사라질 블록이 아니고, 4개의 블록쌍을 이룬다면, 해당 좌표들의 `check` 를 `true` 로 바꿔 후에 사라질 것임을 저장한다.

## Review
- 시키는 대로 하면 되는 문제였다.