# [64064] 불량 사용자 - Python

## 🔍 Algorithm
**문자열, 순열**

## 💻 Logic

```Python
    order_list = permutations(user_id, len(banned_id))  # 순열 생성
    for order in order_list:
        check = True
        for i, id in enumerate(banned_id):
            # 정규 표현식 사용 (^: 시작 부분 매치 확인, $: 끝 부분 매치 확인)
            pattern = re.compile(f"^{id.replace('*', '.')}$")   # '*'는 '.'으로 치환
            if not pattern.match(order[i]): check = False
        # 전부 다 일치하는 경우
        if check:
            order = sorted(list(order)) # 중복 제거를 위해 우선 정렬
            result.append(tuple(order)) # set 처리하기 위해 tuple로 변환 후 append
    return len(set(result)) # set 이용해서 중복 제거
```
- **순열 생성**  
    **permutations**를 사용해서 `user_id` 배열에서 `len(banned_id)`개의 원소를 뽑는 순열을 만든다.  
    생성된 모든 순열을 비교하면서 경우의 수 확인  
- **정규 표현식 사용**  
    `f` 포매팅과 `replace`를 이용하여 `*` 문자를 `.` 으로 변환하고,  
    `^` 메타문자와 `$` 메타문자를 사용하여 시작 부분과 끝 부분 모두 매칭하는지 확인할 **정규 표현식** 패턴 생성  
- **중복 제거**  
    순열의 문자열이 다 일치하는 경우에는 중복 제거를 위해 `sorted`를 사용해서 우선 정렬하고,  
    **set** 처리를 위해 **tuple**로 변환 후 `result` 리스트에 **append** 해준다.  
    이 후, **set**으로 변환해 중복을 제거해주고 길이 반환  


## 📝 Review

처음 보자마자 **순열**을 생성해서 경우의 수를 비교해야겠다고 생각했고, **정규 표현식**을 공부한 후 **정규 표현식** 활용해서 풀었다.  
당연히 **순열** 문제로 생각했는데 거의 다 **DFS**나 **비트마스크**로 풀었다는게 신기,,  
중복 제거를 위해 **list**로 변환해 **정렬**하고, **tuple**로 변환해서 **append**하고, 다시 **set** 처리하는 과정에서 실행 시간이 오래 걸린 것 같다...  