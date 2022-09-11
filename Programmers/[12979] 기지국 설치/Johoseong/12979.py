def solution(n, stations, w):
    answer = 0
    not_5g = []

    s, e = 1, 1
    for station in stations:
        e = station - w
        not_5g.append(e - s)
        s = station + w + 1
    if (stations[-1] + w) < n:
        not_5g.append(n + 1 - s)

    for cnt in not_5g:
        if cnt <= 0: continue
        if cnt <= (2 * w + 1):
            answer += 1
        else:
            answer += (cnt // (2 * w + 1))
            if cnt % (2 * w + 1) != 0:
                answer += 1
    return answer

print(solution(16, [1, 6, 14], 1))