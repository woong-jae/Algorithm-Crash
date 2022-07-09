# [72412] 순위 검색
## Algorithm
- 조합, Binary Search
## Logic
### 처음 풀이
- 조합 안쓰고 info 정보 자체만 dictionary에 넣었음
- 이후에 교집합으로 답 구했다가 효율성 0점 떴음..
- 마지막에 점수 구하는 부분 이진탐색으로 바꿨는데도 효율성 0됨. 근본적으로 query에 해당하는 값을 O(1)로 뽑아내야 효율성 통과였음

### 바뀐 풀이
- 처음에 info마다 가능한 조합을 모두 구해서 dictionary 넣어두면, query에 해당하는 조합을 빠르게 찾을 수 있음
1. info마다 가능한 조합 만들어서 dictionary에 점수를 value로 넣음
```python
for i in info:
    rows = i.split()
    for c in range(5):
        key = list(combinations(rows[:-1], c))
        for k in key:
            tmp = ''.join(k)
            tables.setdefault(tmp, [])
            tables[tmp].append(int(rows[-1]))
```
2. 각 조합마다 점수 오름차순 정렬 (나중에 이진탐색 해야됨)
```python
for t in tables:
    tables[t].sort()
```
3. dictionary에서 해당하는 query의 점수 뽑아냄 -> 이진탐색(lower bound)로 x값 이상인 점수의 갯수 셈
- query가 dictionary에 없는 경우 처리해주는 것에 유의. 안하면 런타임 에러뜸..
```python
for q in query:
    tmp = q.split()
    key = ''
    for t in tmp[:-1]:
        if t == '-' or t == 'and': continue
        key += t
    if not tables.get(key):
        answer.append(0)
        continue
    index = binary_search(tables[key], int(tmp[-1])) # 꺼낸 점수들에서 x 이상인 값들 이진탐색으로 count
    answer.append(len(tables[key]) - index)
```

## Review
레벨2..?? 처음에 푼 방법은 아무리 해도 O(N^2)였는데 (교집합 연산이 O(N)이었다.) 쿼리에 해당하는 점수를 한번에 찾을 수 있는 방법 찾기가 핵심이었는 듯. 효율성 어렵다 흑