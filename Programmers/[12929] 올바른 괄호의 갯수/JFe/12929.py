def factorial(n):
    if n == 1: return 1
    return n * factorial(n-1)
    
def solution(n):
    return factorial(2 * n) / (factorial(n) * factorial(n + 1))