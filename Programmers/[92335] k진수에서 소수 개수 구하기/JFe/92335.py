# 소수 판별 함수
def is_prime(num):
    if num == 1: return False
    # 2 ~ num의 제곱근 까지만 체크
    for i in range(2, int(num**0.5) + 1):
        if num % i == 0:
            return False
    return True
# 진수 변환 함수
def convert(num, base):
    temp = "0123456789ABCDEF"
    q, r = divmod(num, base)
    if q == 0:
        return temp[r]
    else:
        return convert(q, base) + temp[r]
    
def solution(n, k):
    answer = 0
    converted_list = convert(n, k).split('0')
    for num in converted_list:
        if num == '': continue
        if is_prime(int(num)): answer += 1
    return answer