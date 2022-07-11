# [92334] 신고 결과 받기
## Algorithm
- 구현
## Logic
- 유저별로 신고당한 횟수+신고한 유저id를 기록하고, 신고당한 횟수가 k이상인 유저는 기록해둔 id를 count 하면 된다.
1. 신고당한 횟수와 신고한 유저의 id를 기록함
```python
for r in report:
    id1, id2 = r.split()
    if id1 not in report_cnt[id2][1]:
        report_cnt[id2][0] += 1
        report_cnt[id2][1].add(id1)
```
2. 신고당한 횟수가 k 이상이면, 신고한 유저 id를 탐색해서 누적 신고 횟수를 cnt
```python
for tmp in report_cnt:
    cnt, ids = report_cnt[tmp]
    if cnt >= k:
        for id in ids:
            answer[id] += 1
```

## Review
쉬운 레벨1 문제였다.