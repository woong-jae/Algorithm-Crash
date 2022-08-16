from collections import deque
import math

def solution(n,a,b):
    ranges = deque()
    for i in range(1, n, 2): # 두개 짝 먼저 만들기
        ranges.append([i, i + 1])
    
    while ranges: # 짝지어진 것들 범위에 a, b 동시에 없으면 짝 두개 이어붙임
        s1, e1 = ranges.popleft()
        if s1 <= a <= e1 and s1 <= b <= e1:
            return int(math.log2(e1 - s1 + 1))
        s2, e2 = ranges.popleft()
        if s2 <= a <= e2 and s2 <= b <= e2:
            return int(math.log2(e2 - s2 + 1))

        ranges.append([min(s1, s2), max(e1, e2)])