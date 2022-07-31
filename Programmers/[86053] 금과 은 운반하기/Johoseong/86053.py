def solution(a, b, g, s, w, t):
    start, end = 0, 4 * (10 ** 5) * (10 ** 9)
    # start, end = 0, 100
    answer = end
    N = len(g)

    while start <= end:
        cur_time = (start + end) // 2
        gold, silver, total = 0, 0, 0

        for i in range(N):
            max_move_cnt = cur_time // (t[i] * 2)
            if cur_time % (t[i] * 2) >= t[i]: # 왕복시간으로 나눈 나머지가 편도로 걸리는 시간보다 크면 +1 (편도 이동가능 의미)
                max_move_cnt += 1

            if w[i] * max_move_cnt > g[i]: # 현재 시간&도시에서, 금 최대 운송 무게
                gold += g[i]
            else:
                gold += (w[i] * max_move_cnt)
            if w[i] * max_move_cnt > s[i]: # 은 최대 운송 무게
                silver += s[i]
            else:
                silver += (w[i] * max_move_cnt)
            if w[i] * max_move_cnt > s[i] + g[i]: # 금 + 은 최대 운송 무게
                total += (s[i] + g[i])
            else:
                total += (w[i] * max_move_cnt)

        if total >= (a + b) and gold >= a and silver >= b:
            end = cur_time - 1 # 시간 더 줄임
            answer = min(answer, cur_time)
        else:
            start = cur_time + 1 # 현재 시간으론 a, b 만족 못함 -> 시간 늘림

    return answer