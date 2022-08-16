# [12985] 예상 대진표
## Algorithm
- 구현?
## Logic
- Bottom-up으로, 경쟁하는 번호들을 범위로 저장하면서 합쳐나간다고 생각하면 쉬움
- 만약 범위 내에 a, b가 없으면 현재 토너먼트의 다른 범위와 합치는 식
1. 먼저 범위를 만듦 (bottom-up이므로 처음 범위는 1-2, 3-4, ... 이렇게 만듦)
```python
ranges = deque()
for i in range(1, n, 2): # 두개 짝 먼저 만들기
    ranges.append([i, i + 1])
```
2. 범위들을 탐색하면서, 해당 범위에 a와 b가 동시에 있으면 a,b가 붙게 된다는 뜻임
- 범위 내에 없으면, 범위를 합쳐나감 (라운드가 올라간다는 의미)
- 범위의 크기는 라운드가 올라가면서 2배씩 커지므로, answer를 구할 땐 log2 시켜줘야함
```python
while ranges: # 짝지어진 것들 범위에 a, b 동시에 없으면 짝 두개 이어붙임
    s1, e1 = ranges.popleft()
    if s1 <= a <= e1 and s1 <= b <= e1:
        return int(math.log2(e1 - s1 + 1))
    s2, e2 = ranges.popleft()
    if s2 <= a <= e2 and s2 <= b <= e2:
        return int(math.log2(e2 - s2 + 1))

    ranges.append([min(s1, s2), max(e1, e2)])
```

## Review
나름 쉬운 문제였다.