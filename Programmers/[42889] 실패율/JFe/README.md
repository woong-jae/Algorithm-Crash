# [42889] 실패율 - Python

## 🔍 Algorithm
**Sort**

## 💻 Logic

```Python
    # 사용자별 현재 스테이지 계산
    for s in stages:
        cur_stage[s] += 1
    # 스테이지별 실패율 계산
    for i in range(1, N+1):
        # 도달한 사람이 없는 경우 (division by zero 피하기 위해)
        if last == 0:
            failure[i] = 0
            continue
        failure[i] = cur_stage[i] / last
        last -= cur_stage[i]
```
- **사용자별 현재 스테이지 계산**  
    해당되는 `stage` 딕셔너리의 값 증가시켜주기  
- **스테이지별 실패율 계산**  
    현재 스테이지의 딕셔너리 값을 `last`로 나눠서 `failure` 딕셔너리에 값을 저장해준다.  
    그 다음, `last`에서 현재 스테이지 값을 빼고 다시 저장해준다.  
    도달한 사람이 없는 경우 (`last`가 **0**이 되면), **division by zero**를 피하기 위해 예외 처리  

```Python
# value 내림차순, key 오름차순 정렬
answer = [key for key, value in sorted(failure.items(), key = lambda x : (-x[1], x[0]))]
```
- **value 내림차순, key 오름차순 정렬**  


## 📝 Review

딕셔너리와 정렬로 풀 수 있는 간단한 문제
