# [87391] 공 이동 시뮬레이션 - Python

## 🔍 Algorithm
**구현**

## 💻 Logic

```Python
    top, bottom, left, right = x, x, y, y
    for flag, dx in queries[::-1]:
        if flag == 0:
            if left != 0: left += dx
            right += dx 
            if right > m - 1: right = m - 1 
        elif flag == 1:
            if right != m - 1: right -= dx
            left -= dx
            if left < 0: left = 0
        elif flag == 2:
            if top != 0: top += dx
            bottom += dx
            if bottom > n - 1: bottom = n - 1
        elif flag == 3:
            if bottom != n - 1: bottom -= dx
            top -= dx
            if top < 0: top = 0
        if top >= n or bottom < 0 or left < 0 or right >= m: return 0
    return (bottom - top + 1) * (right - left + 1)
```

- **쿼리를 뒤에서부터 거꾸로 실행해서 가능한 좌표 범위 찾기**  
- **마지막에 해당 좌표 범위 넓이만큼이 시작점의 개수**
    

## 📝 Review

문제 자체는 그 2048 게임이었나 비슷한 게임 있어서 이해 쉬웠는데  
뒤에서부터 거꾸로 구현해서 그 범위만큼 넓이를 구해서 정답을 찾는다..? 이런 방법은 상상도 못했다.. 어렵다..  
