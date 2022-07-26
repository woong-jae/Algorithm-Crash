def solution(lottos, win_nums):
    MAX = 0
    MIN = 0

    for l in lottos:
        if l == 0:
            MAX += 1
            continue
        if l in win_nums:
            MAX += 1
            MIN += 1
    if MAX == 0: MAX = 1
    if MIN == 0: MIN = 1
    return [7 - MAX, 7 - MIN]

print(solution([0, 0, 0, 0, 0, 0], [38, 19, 20, 40, 15, 25]))