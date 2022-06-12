# [42889] 실패율
## Algorithm
- 구현
## Logic
- 스테이지마다 클리어한 사람과 못 깬 사람 카운트해서 실패율 구하면 됨!
1. 클리어한 스테이지와 못 깬 스테이지 체크
```python
for s in stages:
    for i in range(1, s):
        stage_user[i] += 1
    stage_user[s] += 1
    not_clear[s] += 1
```
2. 스테이지마다 실패율 구해서 내림차순 정렬
```python
for i in range(N):
    if stage_user[i + 1] == 0:
        answer.append([i + 1, 0])
        continue
    answer.append([i + 1, Decimal(str(not_clear[i + 1])) / Decimal(str(stage_user[i + 1]))])
    answer.sort(key=lambda x: x[1], reverse=True)
```

## Review
쉬운 문제였다.