import sys

def calc(a, x):
    # base case
    if x == 1: 
        return a % C
    # 지수를 2씩 나눠서 분할 정복
    temp = calc(a, x // 2)
    # 짝수인 경우
    if x % 2 == 0:
        return temp * temp % C
    # 홀수인 경우
    else: 
        return temp * temp * a % C

A, B, C = map(int, sys.stdin.readline().split())
print(calc(A, B))