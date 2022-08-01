# [86053] 금과 은 운반하기 - Python

## 🔍 Algorithm
**Binary Search**

## 💻 Logic

```Python
    # 이분 탐색
    while left <= right:
        mid = (left + right) // 2
        gold_cnt, silver_cnt, total_cnt = 0, 0, 0
        # 도시별 이동
        for i in range(len(w)):
            # 현재 mid 시간에서 왕복으로 이동할 수 있는 횟수 계산
            move_cnt = mid // (t[i] * 2)
            # 마지막에 편도로 이동할 수 있으면 횟수 +1
            if mid % (t[i] * 2) >= t[i]: move_cnt += 1
            # 옮길 수 있는 금 계산
            if w[i] * move_cnt <= g[i]: gold_cnt += w[i] * move_cnt
            else: gold_cnt += g[i]
            # 옮길 수 있는 은 계산
            if w[i] * move_cnt <= s[i]: silver_cnt += w[i] * move_cnt
            else: silver_cnt += s[i]
            # 옮길 수 있는 전체 양 계산
            if w[i] * move_cnt <= s[i] + g[i]: total_cnt += w[i] * move_cnt
            else: total_cnt += s[i] + g[i]
        # 옮길 수 있는 금이 a 이상, 은이 b 이상, 전체 양이 a+b 이상이면 right 옮겨주고 answer 저장
        if gold_cnt >= a and silver_cnt >= b and total_cnt >= a + b:
            right = mid - 1
            answer = min(answer, mid)
        # 아니면 left 변경
        else:
            left = mid + 1
```
- **Binary Search**  
    `left` : `0`  
    `right` : `10**15` (최악의 경우)  
    left, right를 옮겨 가면서 이분 탐색  
- **도시별 이동**  
    - 현재 `mid` 시간에서 왕복으로 이동할 수 있는 횟수 계산 (마지막에 편도로 이동할 수 있으면 횟수 +1)  
    - 옮길 수 있는 금, 은, 전체 양 계산  
- 옮길 수 있는 금이 `a` 이상, 은이 `b` 이상, 전체 양이 `a+b` 이상이면 `right` 옮겨주고 `answer` 저장 / 아니면 `left` 변경  


## 📝 Review

이분 탐색으로 구현해야겠다고 생각은 했지만 구현하기 어려워서 결국 다른 풀이를 참고했다..  
이분 탐색 문제는 항상 구현 과정에서 어려움을 겪는데 이분 탐색 문제만 골라서 많이 풀어봐야지ㅜ  
