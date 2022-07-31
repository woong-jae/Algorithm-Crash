import sys
def solution(a, b, g, s, w, t):
    answer = sys.maxsize
    left, right = 0, 10**15
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
    return answer