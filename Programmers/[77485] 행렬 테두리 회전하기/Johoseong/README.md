# [77485] 행렬 태두리 회전하기
## Algorithm
- 구현
## Logic
- 4개의 테두리마다 for문을 이용하여 회전을 해서 행렬을 갱신해주고, 그 때 가장 작은 값을 answer에 추가해주면 됨
- for문으로 덮어쓰기 할 때, 재일 왼쪽 아래의 값과 제일 오른쪽 위의 값은 사라지므로, 따로 tmp에 넣어서 갱신해줌에 유의
```python
def rotate(matrix, x1, y1, x2, y2):
    min_num = 10 ** 10
    left_tmp = matrix[x2][y1]
    right_tmp = matrix[x1][y2]
    for c in range(y2 - 1, y1 - 1, -1): # 위
        matrix[x1][c + 1] = matrix[x1][c]
        min_num = min(min_num, matrix[x1][c])
    for c in range(y1 + 1, y2 + 1): # 아래
        matrix[x2][c - 1] = matrix[x2][c]
        min_num = min(min_num, matrix[x2][c])

    for r in range(x1 + 1, x2 + 1): # 왼쪽
        matrix[r - 1][y1] = matrix[r][y1]
        min_num = min(min_num, matrix[r][y1])
    for r in range(x2 - 1, x1 - 1, -1): # 오른쪽
        matrix[r + 1][y2] = matrix[r][y2]
        min_num = min(min_num, matrix[r][y2])

    matrix[x1 + 1][y2] = right_tmp
    matrix[x2 - 1][y1] = left_tmp
    min_num = min(min_num, right_tmp)
    min_num = min(min_num, left_tmp)
    return min_num
```

## Review
행렬의 테두리 회전은 자주 나오는 문제같다.