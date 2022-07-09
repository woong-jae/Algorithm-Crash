def convert(n, k): # 진수 변환 메소드
    tmp = n
    convert_num = ''
    while True:
        if tmp < k:
            convert_num = str(tmp) + convert_num
            break
        leaves = tmp % k
        tmp = tmp // k
        convert_num = str(leaves) + convert_num
    return convert_num

def is_prime(n):
    if n == 1: return False
    for i in range(2, int(n ** 0.5) + 1): # root(n)까지만 판별해도 됨!
        if n % i == 0:
            return False
    return True

def solution(n, k):
    answer = 0    
    number = convert(n, k) # k진수로 변환
    tmp = number.split('0') # 0 기준으로 나눔
                
    for t in tmp: # 나눠진 숫자들이 소수인지 판별
        if t and is_prime(int(t)):
            answer += 1
    return answer