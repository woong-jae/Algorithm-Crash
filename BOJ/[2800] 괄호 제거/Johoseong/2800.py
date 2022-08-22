import sys
from itertools import combinations
input = sys.stdin.readline        

arr = input()
index, stack = [], []
combination = []
result = set() # 중복 존재하는 경우 제거하기 위해서

#괄호끼리 짝지음
for i, a in enumerate(arr):
    if a == '(':
        stack.append(i)
    if a == ')':
        index.append([stack.pop(), i])

#괄호쌍들 조합
for i in range(1, len(index) + 1):
    combination.extend(list(combinations(index, i)))

for case in combination:
    tmp = list(arr)
    for i in case:
        for j in i:
            tmp[j] = ''
    result.add(''.join(tmp).rstrip())
for i in sorted(result):
    print(i)