# [68646] 풍선 터트리기
## Algorithm
- 구현
## Logic
- 범위 때문에 DFS식으로 구하면 안됨
1. 탐색할 값 기준으로 좌 우 값들의 최솟값 보다도 작으면 일단 남을 수 있음
2. 가장 큰 값은/ 가장 작은 값 남을 수 있음
- 가능한 값들 남기면 됨
```python
for i in range(len(a)):
    if left > a[i]:
        left = a[i]
    matrix[0][i] = left

for i in range(len(a) - 1, -1, -1):
    if right > a[i]:
        right = a[i]
    matrix[1][i] = right
```

## Review
남는 조건을 못찾겠어서 질문하기 봤음.. 