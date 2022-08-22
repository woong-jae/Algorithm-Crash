import sys
input = sys.stdin.readline

def kmp(string, pattern):
    table = [0 for _ in range(len(pattern))]
    i = 0
    for j in range(1, len(pattern)):
        while i > 0 and pattern[i] != pattern[j]:
            i = table[i - 1]
        if pattern[i] == pattern[j]:
            i += 1
            table[j] = i

    result = []
    i = 0
    for j in range(len(string)):
        while i > 0 and pattern[i] != string[j]:
            i = table[i - 1]
        if pattern[i] == string[j]:
            i += 1
            if i == len(pattern):
                result.append(j - i + 1)
                i = table[i - 1]
    return result

text = input().strip()
pat = input().strip()
num_remove = ""

for t in text:
    if '0' <= t <= '9': continue
    num_remove += t

print(1 if kmp(num_remove, pat) else 0)