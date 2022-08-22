def solution(n):
    a, b = 1, 2
    if n == 1: return 1
    if n == 2: return 2

    for _ in range(3, n + 1):
        c = a + b
        a, b = b, c
        # b = c
    return c % 1000000007