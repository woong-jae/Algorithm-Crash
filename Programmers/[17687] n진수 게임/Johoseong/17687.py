def convert(num, n):
    rev_base = ''
    map = {10: 'A', 11: 'B', 12: 'C', 13: 'D', 14: 'E', 15: 'F'}
    if num == 0:
        return '0'
    while num > 0:
        num, mod = divmod(num, n)
        if mod >= 10:
            mod = map[mod]
        rev_base += str(mod)
    return rev_base[::-1] 
    
def solution(n, t, m, p):
    answer = ''
    index = [p - 1]
    
    while len(index) < t:
        index.append(index[-1] + m)

    last = index[-1]
    num = ''
    cur = 0
    while len(num) <= last:
        num += convert(cur, n)
        cur += 1
    
    for i in index:
        answer += num[i]

    return answer