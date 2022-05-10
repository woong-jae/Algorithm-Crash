# [81302] 거리두기 확인하기
## Algorithm
- 구현
## Logic
1. ```P1```를 발견
2. ```P1```로부터 맨헤튼거리가 2인 곳에 또 다른 ```P2```가 있는지 검사 (거리 1이면 무조건 0 리턴)
3. ```P1```과 ```P2``` 사이에 **파티션이 존재하는지** 검사함
- 직선거리로 2 -> 사이에 파티션 하나 있는지 검사
```python
if r1 == r2:
    if room[r1][int((c1 + c2) / 2)] == 'X':
        return 1
elif c1 == c2:
    if room[int((r1 + r2) / 2)][c1] == 'X':
        return 1
```
- 꺾어서 2 -> 서로 대각선에 파티션 있는지 검사
```python
else: # 대각선 경우
    if r1 > r2:
        if room[r1 - 1][c1] == 'X' and room[r2 + 1][c2] == 'X':
            return 1
    else:
        if room[r2 - 1][c2] == 'X' and room[r1 + 1][c1] == 'X':
            return 1
```
- 위 두 조건에서 파티션 없으면 0 리턴
- 시간복잡도 O(N)

## Review
간단한 문제였다. 처음에 BFS 풀다가 귀찮아서 그냥 조건 줄줄이 구현했는데 풀렸다.
