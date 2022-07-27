def solution(n):
    mapping = { 0: '1', 1: '2', 2: '4'}
    result = ''

    while n > 0:
        n -= 1
        mod = n % 3
        n = n // 3
        result = mapping[mod] + result
    return result