def solution(enroll, referral, seller, amount):
    N = len(enroll)
    costs = { e: 0 for e in enroll }
    indexs = { enroll[i]: i for i in range(N) }
    indexs['-'] = -1
    costs['-'] = 0

    for a, s in enumerate(seller):
        i = indexs[s] # 현재 직원의 인덱스
        cur = enroll[i]
        parent = ''
        cur_cost = amount[a] * 100
        costs[cur] += cur_cost

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
    
    costs.pop('-')
    return list(costs.values())