# [42894] 블록 게임 - Python

## 🔍 Algorithm
**구현**

## 💻 Logic

```Python
# 빈 공간 채워넣을 수 있는지 확인(x좌표 고정)
    def find_fill(y, x):
        for i in range(y):
            if board[i][x]: return False
        return True
```
- **빈 공간 채워넣을 수 있는지 확인하는 함수**  
    x좌표를 고정해두고 y좌표 내려오면서 빈 공간이 아닌 공간이 있으면 **False** 반환  

```Python
# 블록 없앨 수 있는지 확인
    def find_delete(y, x, height, width):
        cnt, last = 0, -1
        if y + height > n or x + width > n: return False    # boundary 체크
        for i in range(y, y + height):
            for j in range(x, x + width):
                # 빈 공간인 경우
                if board[i][j] == 0:
                    if not find_fill(i, j): return False    # 채울 수 없으면 False
                    cnt += 1
                    if cnt > 2: return False    # 빈 공간이 3개 이상이면 False
                # 빈 공간 아닌 경우
                else:
                    if last == -1: last = board[i][j]   # 색깔 저장
                    elif last != board[i][j]: return False  # 색깔이 다르면 False
        # 블록 없애기
        for i in range(y, y + height):
            for j in range(x, x + width):
                board[i][j] = 0
        return True
```
- **블록 없앨 수 있는지 확인하는 함수**  
    - **boundary** 벗어나는지 확인하고, 벗어나면 **False** 반환  
    - 반복문 돌면서 블록 없앨 수 있는지 확인  
    - **빈 공간인 경우** : `find_fill` 함수를 이용해서 채울 수 있는지 확인하고 채울 수 없으면 **False** 반환 / 빈 공간이 **2**개 넘으면 **False** 반환  
    - **빈 공간 아닌 경우** : 색깔 저장해두고, 저장해둔 색과 다르면 **False** 반환  
- **블록 없애기**  
    - 블록을 없앨 수 있으면 다시 반복문 돌면서 해당 위치 전부 **0**으로 변환  

```Python
# 없앨 수 있는 블록이 없을 때까지 반복
    while True:
        count = 0
        for i in range(n):
            for j in range(n):
                # 2X3 형태, 3X2 형태 각각 검사
                if find_delete(i, j, 2, 3) or find_delete(i, j, 3, 2): count += 1
        if count == 0: break
        answer += count
```
- **없앨 수 있는 블록이 없을 때까지 반복**  
    **2X3 형태**, **3X2 형태**로 각각 `find_delete` 함수를 이용해서 블록을 없앨 수 있는지 확인하고,  
    없앨 수 없으면 **break**  

## 📝 Review

도저히 구현 방법을 모르겠어서 풀이를 참고했다..  
풀이를 보면 이해가 쉬운데 단순 구현 문제는 마음을 편하게 하고 멀리 봐야겠다,,
