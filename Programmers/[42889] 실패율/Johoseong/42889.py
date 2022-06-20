from decimal import Decimal
def solution(N, stages):
    answer = []
    result = []
    stage_user = [0] * (N + 2)
    not_clear = [0] * (N + 2)

    for s in stages:
        for i in range(1, s):
            stage_user[i] += 1
        stage_user[s] += 1
        not_clear[s] += 1
    for i in range(N):
        if stage_user[i + 1] == 0:
            answer.append([i + 1, 0])
            continue
        answer.append([i + 1, Decimal(str(not_clear[i + 1])) / Decimal(str(stage_user[i + 1]))])
    answer.sort(key=lambda x: x[1], reverse=True)

    for i in answer: result.append(i[0])
    return result

print(solution(4, [4, 4, 4, 4, 4]))