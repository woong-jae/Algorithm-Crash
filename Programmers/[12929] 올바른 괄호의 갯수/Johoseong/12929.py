def count(l, r, n):
    global answer
    if l == n and r == n:
        answer += 1
        return

    if l < n:
        count(l + 1, r, n)
        if l > r:
            count(l, r + 1, n)
    else:
        count(l, r + 1, n)

def solution(n):
    global answer
    answer = 0
    count(1, 0, n)
    return answer