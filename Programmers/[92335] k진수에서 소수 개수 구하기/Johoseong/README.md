# [92335] k진수에서 소수 개수 구하기
## Algorithm
- 진수 변환?
## Logic
- k진수로 변환하고 소수 판별만 시간초과 안나게 잘하면 되는 문제였다.
1. n을 k진수로 변환함
- k로 나눈 나머지 계속 구하면 됨
```python
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
```
2. 진수 변환으로 나온 값을 0 기준으로 split
3. 0 기준으로 나눠진 숫자들이 소수인지 판별, 소수면 count+1
- 소주 판별 시 root(n)까지 나눠야지 시간 초과 안남에 유의
```python
def is_prime(n):
    if n == 1: return False
    for i in range(2, int(n ** 0.5) + 1): # root(n)까지만 판별해도 됨!
        if n % i == 0:
            return False
    return True
```

## Review
쉬운 문제였다. 처음에 소수 판별할 때 n까지 나눴다가 시간초과나서 root(n) 하니까 통과됨 !