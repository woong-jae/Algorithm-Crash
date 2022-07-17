def solution(lottos, win_nums):
    correct, zero, answer = 0, 0, []
    for l in lottos:
        # 0 체크
        if l == 0:
            zero += 1
            continue
        # 맞는 번호 체크
        for n in win_nums:
            if l == n: correct += 1
    # 최고 순위
    if correct + zero > 1: answer.append(7 - correct - zero)
    else: answer.append(6)
    # 최저 순위
    if correct > 1: answer.append(7 - correct)
    else: answer.append(6)
    return answer