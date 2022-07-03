# [72411] 메뉴 리뉴얼 - Python

## 🔍 Algorithm
**조합, 정렬**

## 💻 Logic

```Python
# 해당 조합을 포함하고 있는 order 수 반환하는 함수
def check(orders, c):
    count = 0
    for order in orders:
        flag = True
        for ch in c:
            if ch not in order:
                flag = False
                break
        if flag: count += 1
    return count
```
- **해당 조합을 포함하고 있는 order 수 반환하는 함수**  

```Python
    for order in orders:
        for num in course:
            # 갯수별 조합 생성
            comb = list(combinations(order, num))
            for c in comb:
                c = sorted(c)
                count = check(orders, c)
                if count > 1:   # 2명 이상 주문한 경우만
                    if result_count[num] <= count:  # 기존 갯수보다 크거나 같은 경우에만 추가
                        result[num].add((count, ''.join(c)))
                        result_count[num] = count
```
- `combinations`를 이용해서 갯수별 조합을 생성하고, `check()`함수를 이용하여 해당 조합을 포함하고 있는 order 수를 반환받는다.  
- 2명 이상 주문한 경우에만 추가해주고,  
- 기존 갯수보다 크거나 같은 경우에만 `result` 딕셔너리에 추가해준다.  

```Python
# 각 개수별 코스요리 집합에서 조건에 맞는 값만 answer에 추가
    for num in course:
        if len(result[num]) == 0: continue
        for cnt, name in result[num]:
            if cnt == result_count[num]:
                answer.append(name)
    answer.sort()
```
- 각 개수별 코스요리 집합에서 조건에 맞는 최댓값만 `answer`에 추가해준다.  
- 정렬 후 `answer` 값 출력  


## 📝 Review

제한사항에서 입력 값의 크기가 크지 않아서 combinations를 이용해 조합을 만들어서 하나씩 비교해도 되겠다고 생각하고 풀었다.  
조금 더 효율적으로 풀어야지라는 생각에 살짝 헤맸던 것 같다,,
