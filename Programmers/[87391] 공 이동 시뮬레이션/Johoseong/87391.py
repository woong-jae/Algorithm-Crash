def solution(n, m, x, y, queries):
    s_r, s_c, e_r, e_c = x, y, x, y
    
    while queries:
        d, dx = queries.pop()
        if d == 0:
            if s_c == 0:
                e_c = min(m - 1, e_c + dx)
            else:
                if s_c + dx >= m: # 불가능 경우
                    return 0
                s_c = min(m - 1, s_c + dx)
                e_c = min(m - 1, e_c + dx)

        elif d == 1:
            if e_c == (m - 1):
                s_c = max(0, s_c - dx)
            else:
                if e_c - dx < 0: # 불가능 경우
                    return 0
                s_c = max(0, s_c - dx)
                e_c = max(0, e_c - dx)

        elif d == 2:
            if s_r == 0:
                e_r = min(n - 1, e_r + dx)
            else:
                if s_r + dx >= n:
                    return 0
                s_r = min(n - 1, s_r + dx)
                e_r = min(n - 1, e_r + dx)
        else:
            if e_r == (n - 1):
                s_r = max(0, s_r - dx)
            else:
                if e_r - dx < 0:
                    return 0
                s_r = max(0, s_r - dx)
                e_r = max(0, e_r - dx)
    
    return (e_r - s_r + 1) * (e_c - s_c + 1)

print(solution(2, 5, 0, 1, [[3, 1], [2, 2], [1, 1], [2, 3], [0, 1], [2, 1]]))