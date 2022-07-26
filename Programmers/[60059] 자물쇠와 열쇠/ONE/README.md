# [60059] 자물쇠와 열쇠
## Algorithm
- **Brute Force**

## Logic
- 열쇠를 4방향으로 rotate 한다
- rotate한 키를 가지고 n x n 크기의 사각형 위를 옮겨 다니게 하며  
- 자물쇠와 값을 합해 1로만 이루어지는 n x n 크기의 사각형이 되는지 확인

```java
public boolean solution(int[][] key, int[][] lock) {
    int m = key.length;
    int n = lock.length;

    for(int c = 0; c < 4; c++) {
        int[][] rotatedKey = rotate(m, c, key);

        for(int i = -m; i < n; i++)
            for(int j = -m; j < n; j++)
                if(isValid(n, move(i, j, n, m, rotatedKey), lock))
                    return true;
    }
    return false;
}
```

## Review
당연히 반복문을 0으로 시작했으나 키는 -m 부터 움직일수 있다는 걸 알아차리는데 오래 걸린것 같다  
사고의 유연함이 부족한 듯
