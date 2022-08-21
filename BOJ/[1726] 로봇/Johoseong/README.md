# [1726] 로봇
## Algorithm
- BFS
## Logic
- 방향이 count에 포함되기 때문에, 위치 + 방향까지 que에 기억해주면서 BFS 탐색을 진행
- 같은 이유로 visited 배열에 방향까지 포함해서 3차원 배열로 방문 유무를 체크해줘야 됨
- ```방향 전환```과 ```직진 이동```이 나눠서 count 되므로, 위치마다 두 경우를 나눠서 구하면 편하게 값 구할 수 있음
```python
for s in range(1, 4): # 직진 이동
    n_r = c_r + D[c_d][0] * s
    n_c = c_c + D[c_d][1] * s
    n_d = c_d
    if not (0 <= n_r < M and 0 <= n_c < N) or matrix[n_r][n_c] == 1: continue
    if visited[n_r][n_c][n_d] == 1: continue
    que.append([n_r, n_c, n_d, c_cnt + s])
    visited[n_r][n_c][n_d] = 1

for n_d in range(4): # 방향 이동
    if visited[c_r][c_c][n_d] == 1: continue
    n_cnt = c_cnt
    if (c_d == 0 and n_d == 1) or (c_d == 1 and n_d == 0) or (c_d == 2 and n_d == 3) or (c_d == 3 and n_d == 2):
        n_cnt += 2
    else:
        n_cnt += 1
    que.append([c_r, c_c, n_d, n_cnt])
```

## Review
알고리즘 자체는 흔히 보이는 문제였는데 구현이 복잡해서 오래걸렸다..