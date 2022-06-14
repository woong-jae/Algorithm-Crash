# [60059] 자물쇠와 열쇠 - Python

## 🔍 Algorithm
**구현**

## 💻 Logic

```Python
    # 처음 제외하고, 시계 방향 회전
        if angle != 0:
            temp = [[0 for _ in range(m)]for _ in range(m)]
            for i in range(m):
                for j in range(m):
                    temp[j][m - 1 - i] = key[i][j]
            key = temp
```
- **90도씩 시계방향 회전**  


```Python
    # key 옮기면서 확인
        for i in range(m + n):
            for j in range(m + n):
                clear = True
                # 큰 배열 grid 만들어서 key 옮기기
                grid = [[-1 for _ in range(n + m * 2)] for _ in range(n + m * 2)]
                for r in range(m):
                    for c in range(m):
                        grid[i+r][j+c] = key[r][c]
                # grid 상 key와 lock 비교
                for r in range(n):
                    for c in range(n):
                        if grid[m-1+r][m-1+c] == -1 and lock[r][c] == 0:    # key가 벗어났는데 그 부분이 자물쇠 홈인 경우
                            clear = False
                        elif grid[m-1+r][m-1+c] != -1 and not grid[m-1+r][m-1+c]^lock[r][c]:   # key와 lock의 값이 같은 경우 (맞지 않는 경우)
                            clear = False
                if clear: 
                    return True
```
- **key 옮기면서 확인**  
    - 큰 배열 `grid` 만들어서 `key` 옮기기  
    - `grid` 상 `key`와 `lock` 비교  
      `key`가 벗어났는데 그 부분이 자물쇠 홈인 경우, `clear = False`  
      `key`와 `lock`의 값이 같은 경우 (맞지 않는 경우), `clear = False`  
    - `clear`가 **True**면 **True** 반환  



## 📝 Review

어떻게 풀어야할지 감을 못잡았다ㅜ  
다른 풀이를 참고한 후, 리스트를 확장해서 풀어야 한다는 점을 알고나서 문제에 나와있는 것처럼 key를 회전시키고 lock과 비교하는 형식으로 구현했다..
