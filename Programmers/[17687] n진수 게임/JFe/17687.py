def convert(num, base):
    temp = "0123456789ABCDEF"
    q, r = divmod(num, base)
    if q == 0:
        return temp[r]
    else:
        return convert(q, base) + temp[r]

def solution(n, t, m, p):
    answer, total, num = '', '', 0
    cur = p-1
    # 진수 변환 (변환 시 길이가 t*m 될 때까지)
    while len(total) <= t * m:
        total += str(convert(num, n))
        num += 1
    # t개까지 말해야 하는 숫자 저장
    while len(answer) < t:
        answer += total[cur]
        cur += m
    return answer