# [87391] 공 이동 시뮬레이션
## Algorithm
- 시뮬레이션
## Logic
- 각 점마다 쿼리를 시뮬레이션하면 시간초과임
- 역으로 생각해서, 목표지점에서 쿼리를 반대로 행하면서 시작점의 범위를 구하면 O(쿼리)만으로 문제를 풀 수 있음
1. 시작점이 될 수 있는 행/열의 범위를 점으로 기억해둠 (행열 * 시작과 끝 = 총 4개의 점)
2. 각 쿼리의 반대 방향으로 시뮬레이션을 하면서 시작 범위를 찾아나감
3. 이 때 주의할 점은, 행렬의 테두리부분과 내부 부분을 잘 처리해줘야함
- 내부에서 이동할 떄는 시작점이 한 점밖에 될 수 없음
- 그러나 테두리에서 이동할 때는 이동방향과 크기에 따라 여러개의 시작점을 가질 수 있음. 이를 범위로 잘 기억하고 처리해야함
```python
s_r, s_c, e_r, e_c = x, y, x, y
while queries:
    d, dx = queries.pop()
    if d == 0:
        if s_c == 0:
            e_c = min(m - 1, e_c + dx)
        else:
            if s_c + dx >= m: # 불가능 경우
                return 0
            s_c = min(m - 1, s_c + dx)
            e_c = min(m - 1, e_c + dx)

    elif d == 1:
        if e_c == (m - 1):
            s_c = max(0, s_c - dx)
        else:
            if e_c - dx < 0: # 불가능 경우
                return 0
            s_c = max(0, s_c - dx)
            e_c = max(0, e_c - dx)

    elif d == 2:
        if s_r == 0:
            e_r = min(n - 1, e_r + dx)
        else:
            if s_r + dx >= n:
                return 0
            s_r = min(n - 1, s_r + dx)
            e_r = min(n - 1, e_r + dx)
    else:
        if e_r == (n - 1):
            s_r = max(0, s_r - dx)
        else:
            if e_r - dx < 0:
                return 0
            s_r = max(0, s_r - dx)
            e_r = max(0, e_r - dx)
```

## Review
방법을 생각하는건 쉬웠는데 구현이 너무 힘들었다.. 지금 풀이대로 행/열의 범위 시작점과 끝점 총 4개를 기억해두면 편하게 풀 수 있는 문제였는데, 난 범위를 갯수로 저장했다가 계속 꼬였음 ㅠ 