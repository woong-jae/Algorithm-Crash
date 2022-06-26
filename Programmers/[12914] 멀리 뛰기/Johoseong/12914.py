def solution(n):
    if n == 1:
        return 1
    jumps = [0 for _ in range(n + 1)]
    jumps[1] = 1
    jumps[2] = 2

    for i in range(3, n + 1):
        jumps[i] = jumps[i - 1] + jumps[i - 2]

    return jumps[n] % 1234567