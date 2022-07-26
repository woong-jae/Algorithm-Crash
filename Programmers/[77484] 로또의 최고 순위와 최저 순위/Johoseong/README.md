# [77484] 로또의 최고 순위와 최저 순위
## Algorithm
- 구현
## Logic
1. ```lottos``` 에서 0인 개수 + ```win_nums```에 포함된 숫자 개수 = 최고 순위
2. ```win_nums```에 포함된 숫자 개수 = 최저 순위
```python
for l in lottos:
    if l == 0:
        MAX += 1
        continue
    if l in win_nums:
        MAX += 1
        MIN += 1
```

## Review
쉬운 문제였다.