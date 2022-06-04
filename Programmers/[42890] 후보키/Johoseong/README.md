# [42890] 후보키
## Algorithm
- 집합, 구현
## Logic
- 컬럼이 8이하로 작아서, 컬럼 조합을 모두 구한 후 최소성과 유일성 여부를 판단하도록 구현.
1. 칼럼 조합을 모두 구함
```python
cols = [i for i in range(col_num)]
combis = []
for i in range(1, col_num + 1):
    combis.append(list(combinations(cols, i)))
```
2. 각 컬럼 조합을 확인하면서, 최소성과 유일성을 검사함
```python
answer = set()
for i in range(col_num):
    for combi in combis[i]:
        is_candidate = True

        for sets in answer: # 최소성 검사
            cnt = 0
            for s in sets:
                if s in combi:
                    cnt += 1
            if len(sets) == cnt:
                is_candidate = False
        if is_candidate == False:
            continue

        sets = []
        for r in relation: # 유일성 검사
            tmp = []
            for c in combi:
                tmp.append(r[c])
            if tmp in sets:
                is_candidate = False
                break
            sets.append(tmp)

        if is_candidate == True:
            answer.add(combi)
```
- 최소성 검사 : 후보키가 된 컬럼 조합은 ```answer``` set에 담는데, 이 answer를 탐색하면서 현재 컬럼 조합이 모두 포함된 후보키가 이미 있는지 확인 -> 없으면 최소성 통과
- 유일성 검사 : ```relation```의 로우에서 현재 컬럼 조합들을 tmp 배열에 담음 -> 중복 없으면 유일성 통과

## Review
구현이 생각보다 번거로웠던 것 같음