# [77485] 행렬 테두리 회전하기 - Python

## 🔍 Algorithm
**구현**

## 💻 Logic

```Python
def rotate(field, x1, y1, x2, y2):
    upper_right, lower_left, lower_right = field[x1][y2], field[x2][y1], field[x2][y2]
    num = min(upper_right, lower_left, lower_right)
    # 상단 가로
    for i in reversed(range(y1, y2)):
        field[x1][i + 1] = field[x1][i]
        num = min(num, field[x1][i])
    # 오른쪽 세로
    for i in reversed(range(x1, x2)):
        field[i + 1][y2] = field[i][y2]
        num = min(num, field[i][y2])
    field[x1 + 1][y2] = upper_right
    # 하단 가로
    for i in range(y1, y2 - 1):
        field[x2][i] = field[x2][i + 1]
        num = min(num, field[x2][i])
    field[x2][y2 - 1] = lower_right
    # 왼쪽 세로
    for i in range(x1, x2 - 1):
        field[i][y1] = field[i + 1][y1]
        num = min(num, field[i][y1])
    field[x2 - 1][y1] = lower_left
    return num
```
- **상단 가로, 오른쪽 세로, 하단 가로, 왼쪽 세로 4가지로 나눠서 계산**  
    꼭짓점 정보를 미리 저장해두고, 각 라인에 맞게 한칸씩 이동  
    이동할 때마다 최솟값 계산해서 num에 저장  

## 📝 Review

예전에도 종종 풀었던 테두리 회전 문제.  
간단한 구현 문제지만 항상 풀 때마다 익숙하지 않아서 빠르게 못풀고 있는데 더 익숙해질 필요가 있어 보임..
