# [86054] 안팉세포
## Algorithm
- DFS, Union-Find
## Logic
### 틀린 풀이
1. dfs를 이용해서 (1) i를 그냥 증가하는 경우와 (2) i는 그대로 두고 세포를 융합하는 경우를 나누어 탐색했음
2. i를 증가할지 말지의 구현은, Y세포의 존재여부와 Y,X 세포의 합을 구해서 경우를 나눔
- Y가 없음 -> (1)만 시행
- Y가 있음 -> Y, X 합이 같음 -> (1), (2) 둘 다 시행 <br>
-> Y, X 합이 다름 -> (1)만 시행
3. Y, X 세포를 융합할 떄 union-find를 사용해서 융합을 구함
```python
if prev == -1: # y가 없음 -> i증가
    dfs(N, i + 1, cells, parent)
else: # y가 있음
    if i_sum == prev_sum: # x,y 세포 합 같음 -> 1. i증가 하거나 2. 세포 융합
        dfs(N, i + 1, cells, parent) # 1.
        parent2 = copy.deepcopy(parent)
        union(parent2, prev, i)
        dfs(N, i, cells, parent2) # 2.
    else: # x,y 세포 합이 다름 -> i증가
        dfs(N, i + 1, cells, parent)
```


## Review
진짜 너무 어렵다 dfs랑 union-find말곤 도저히 생각이 안나는데.. 풀이 보니까 prefix sum은 그렇다쳐도 dp를 도대체 어떻게 쓰지..?