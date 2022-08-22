import sys
from itertools import combinations

s = sys.stdin.readline()
stack, arr, answer = [], [], set()
# 괄호 인덱스 저장
for i, c in enumerate(s):
    if c == '(': stack.append(i)
    elif c == ')': arr.append([stack.pop(), i])
# 인덱스별 조합 생성 후 괄호 제거
for i in range(1, len(arr)+1):
    comb = list(combinations(arr, i))
    for c in comb:
        temp = list(s)
        for a, b in c:
            temp[a], temp[b] = '', ''
        answer.add(''.join(temp[:-1]))
for a in sorted(answer): print(a)