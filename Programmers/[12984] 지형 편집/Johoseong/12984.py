def calc_actions(height, cur, P, Q):
    if cur == height: # 아무거도 안해도 됨
        return 0
    elif cur < height: # 쌓아야 함
        return (height - cur) * P
    else: # 제거해야 함
        return (cur - height) * Q

def calc_cost(height, P, Q, N, land):
    cost_mid, cost_right = 0, 0
    for r in range(N):
        for c in range(N):
            cost_mid += calc_actions(height, land[r][c], P, Q)
            cost_right += calc_actions(height + 1, land[r][c], P, Q)
    return cost_mid, cost_right

def solution(land, P, Q):
    answer = 10 ** 10
    N = len(land)
    left, right = 0, 0
    for l in land:
        right = max(l)

    while left <= right:
        mid = (left + right) // 2
        cost_mid, cost_right = calc_cost(mid, P, Q, N, land)

        if cost_mid == cost_right:
            answer = min(answer, cost_mid)
            break
        elif cost_mid > cost_right:
            answer = min(answer, cost_right)
            left = mid + 1
        else:
            answer = min(answer, cost_mid)
            right = mid - 1

    return answer

print(solution([[1, 2], [2, 3]], 3, 2))