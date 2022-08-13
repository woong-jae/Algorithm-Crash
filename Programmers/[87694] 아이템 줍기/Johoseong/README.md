# [87694] 아이템 줍기
## Algorithm
- BFS, 구현
## Logic
- 크게 1. 테두리 그리기 2. 테두리따라, item까지 거리 구하기 두 단계를 생각해야함
1. 1을 잘 구현하는게 중요. 먼저 겹치는 부분 고려하지 않고 모든 ```rectangle```에 대해 테두리를 1로 표시
- 다 표시 후 다시 ```rectangle```을 돌면서 (단, 행과 열 모두 1씩 줄여서 확인) 해당 사각형의 내부에 1로 표시된 것을 다시 0으로 바꾸면 겹치는 부분을 없앨 수 있음!
```python
def make_line(matrix, rectangle):
    cnt = 1
    for x1, y1, x2, y2 in rectangle:
        for c in range(x1 * 2, x2 * 2 + 1):
            matrix[y1 * 2][c] = cnt
            matrix[y2 * 2][c] = cnt
        for r in range(y1 * 2, y2 * 2 + 1):
            matrix[r][x1 * 2] = cnt
            matrix[r][x2 * 2] = cnt

    for x1, y1, x2, y2 in rectangle: # 겹치는 부분 지우기 (안쪽만 지워야됨)
        for c in range(x1 * 2 + 1, x2 * 2):
            for r in range(y1 * 2 + 1, y2 * 2):
                matrix[r][c] = 0
```
2. 2의 item까지 거리 구하기는 테두리만 잘 구한다면 BFS로 쉽게 구할 수 있음
3. 또 중요한 점 : 테스트케이스 1번처럼 안으로 1칸 오목한 경우가 단순히 50*50 행렬로 생각하면 예외발생하기 쉬움 -> 스케일을 2배로 늘려서 구하면 예외 처리를 하기 쉬움

## Review
귀찮지만 엄청 어려운 문제는 아니었다. 그런데 그 테케1 오목한 부분 때문에 힘들었다.. 2배로 생각하는건 질문하기 보고 알았음.