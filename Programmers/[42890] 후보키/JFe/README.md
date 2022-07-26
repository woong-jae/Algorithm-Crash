# [42890] 후보키 - Python

## 🔍 Algorithm
**조합**

## 💻 Logic

```Python
def check_uniqueness(relation, comb, answer_idx):
    tmp_set = set()
    for i in range(len(relation)):
        tmp = ''
        for c in comb: tmp += relation[i][c]
        tmp_set.add(tmp)
    if len(tmp_set) == len(relation): return True
    return False
```
- **유일성 체크 함수**  
    - 조합에 해당하는 문자열들을 다 더해서 `tmp_set`에 추가한다. (`tmp_set`는 **set**으로 **중복 제거**됨)  
    - 기존 `relation`과 크기를 비교해서 중복 제거된 부분이 없으면 **True**를 반환  

```Python
def check_minimality(answer_idx, comb):
    for i in answer_idx:
        if i.issubset(set(comb)): 
            return False
    return True
```
- **최소성 체크 함수**  
    - `answer_idx`에 있는 값과 **subset**을 비교  
    - 부분집합에 해당되면 **False** 반환  

```Python
# 조합 생성 후, 후보키 확인
    for i in range(len(relation[0])):
        comb_list = combinations([int(x) for x in range(len(relation[0]))], i+1)
        for comb in comb_list:
            comb = list(comb)
            # 최소성 체크
            if not check_minimality(answer_idx, comb): continue
            # 유일성 체크
            if check_uniqueness(relation, comb, answer_idx):
                answer += 1
                answer_idx.append(set(comb))
```
- **조합 생성 후, 후보키 확인**  
    **최소성**을 만족하지 못하면, **continue**  
    **유일성**을 만족하면, `answer` **+1** 해주고, `answer_idx`에 해당 조합 추가  


## 📝 Review

조합을 이용해서 풀면 되겠다고 바로 떠올라서 조합을 만들고 후보키가 될 수 있는지 확인하는 방식으로 구현했다.  
유일성 체크하는 부분은 문자열이라는 특성을 살려 쉽게 할 수 있었고, 최소성 체크를 잘못 이해했었는데 `issubset`을 통해 부분집합을 확인하는 방식으로 바꿔서 해결했다.  
