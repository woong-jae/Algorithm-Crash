# [12984] 지형 편집
## Algorithm
- Binary Search
## Logic
1. 높이 크기가 10억으로 매우 큼, 그래서 높이 기준 이분 탐색을 해야함.
2. 그러나 일반 이분탐색과 다른 점은 이분 탐색의 기준이 원하는 answer가 아니라는 점임. so, left와 right를 결정 짓는 다른 로직이 필요함.
3. 이를 결정 지으려면 ```mid + 1``` 기준의 cost를 추가로 구해서 ```mid```로 구한 cost와 비교해서 탐색 기준을 정해야함!
- 두 값이 같음 : 그게 최소임.
- mid + 1이 더 작음 : right 쪽에 최소 cost 있을 수 있음
- mid가 더 작음 : left 쪽에 최소 cost 있을 수 있음
```python
def calc_cost(height, P, Q, N, land):
    cost_mid, cost_right = 0, 0
    for r in range(N):
        for c in range(N):
            cost_mid += calc_actions(height, land[r][c], P, Q)
            cost_right += calc_actions(height + 1, land[r][c], P, Q)
    return cost_mid, cost_right

...

    if cost_mid == cost_right:
        answer = min(answer, cost_mid)
        break
    elif cost_mid > cost_right:
        answer = min(answer, cost_right)
        left = mid + 1
    else:
        answer = min(answer, cost_mid)
        right = mid - 1
```

## Review
이분 탐색 맞는 것 같은데 효율성 하나밖에 통과 못함. 검색해보니까 다들 이진 탐색으로 푼 것 같은데 뭘까..