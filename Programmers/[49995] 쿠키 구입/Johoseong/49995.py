def solution(cookie):
    answer = 0
    N = len(cookie)

    for i in range(N - 1):
        old, young = i, i + 1
        old_sum, young_sum = cookie[i], cookie[i + 1]

        while True:
            if old_sum == young_sum:
                answer = max(answer, old_sum)

            if old_sum <= young_sum:
                if old <= 0: break
                old -= 1
                old_sum += cookie[old]
            else:
                if young >= N - 1: break
                young += 1
                young_sum += cookie[young]
    return answer