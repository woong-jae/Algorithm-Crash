# [16472] 고냥이 - Python

## 🔍 Algorithm
**Two Pointers**

## 💻 Logic

```Python
# start, end 투 포인터 사용
for end in range(len(s)):
    alpha[s[end]] += 1
    # N개 이하의 알파벳을 가지는 경우
    if len(alpha) <= N:
        result = max(result, end - start + 1)   # result와 최댓값 비교 후 저장
    # N개 보다 많이 알파벳을 가지는 경우
    while len(alpha) > N:
        alpha[s[start]] -= 1
        if alpha[s[start]] == 0: del alpha[s[start]]    # 해당 키 값이 0이 되면 dictionary에서 삭제
        start += 1
print(result)
```
- **start, end 투 포인터 사용**  
    - `end`가 문자열의 처음부터 끝까지 이동하면서 해당 문자의 **dictionary** 값을 **+1**  
    - **N개 이하의 알바펫을 가지는 경우**에는 `result`와 **최댓값** 비교 후 저장  
    - **N개 보다 알파벳을 많이 가지는 경우**에는 `start`에 해당하는 키 값을 **-1** 하고, `start`를 다음 위치로 옮기고 반복  
      이 때, 해당 키 값이 **0**이 되면 **dictionary**에서 삭제  


## 📝 Review

최근에 **투 포인터** 문제를 몇 번 봤어서 바로 **투 포인터** 알고리즘으로 접근해서 풀어야겠다고 생각했다.  
다른 **투 포인터** 문제들과 큰 차이 없는 전형적인 **투 포인터** 문제  
