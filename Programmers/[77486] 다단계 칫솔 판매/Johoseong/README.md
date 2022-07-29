# [77486] 다단계 칫솔 판매
## Algorithm
- 구현?
## Logic
- 높이 1만큼의 부모-자식노드 간에서 매출을 나눠주는게 루트노드까지 반복되는 거고, 현재의 부모노드는 이미 문제에서 주어줬기 때문에 굳이 tree 만들 필요없었음. 주어진대로 구현만 하면 되는 문제이다.
- cur과 parent를 두고, cur의 수익과 parent에게 분배될 수익을 계산하는 것을 반복함 (parnet가 루트'-'거나 배분될 수익이 1 미만일 때까지)
```python
while parent != '-':
    parent = referral[i]

    cost = cur_cost
    parent_cost = int(cost * 0.1)
    cur_cost = cost - int(parent_cost)

    if parent_cost < 1: break # 10%가 1보다 작으면 부모에게 분배 안해도 됨

    costs[cur] -= parent_cost # 내 매출에서 10% 차감
    costs[parent] += parent_cost # 부모는 10% 얻음
    i = indexs[parent]
    cur = enroll[i]
    cur_cost = parent_cost
```

## Review
구현 도중 꼬이지만 않으면 수월하게 풀 수 있는 문제였다. 
