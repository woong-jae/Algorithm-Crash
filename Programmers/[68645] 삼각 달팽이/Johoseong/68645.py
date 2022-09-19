def solution(n):
    answer = [[0] * n for _ in range(n)]
    r, c, cnt = -1, 0, 1

    for i in range(n): # 한변 이동 길이
        for _ in range(i, n): # 이동 방향
            if i % 3 == 0:
                r += 1
            elif i % 3 == 1:
                c += 1
            else:
                r -= 1
                c -= 1

            answer[r][c] = cnt
            cnt += 1
    result = []
    for i in range(n):
        for c in answer[i]:
            if c == 0: continue
            result.append(c)

    return result