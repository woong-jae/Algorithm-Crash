import sys
input = sys.stdin.readline

N = int(input())
arr = list(map(int, input().split(" ")))
S = int(input())

for i in range(N - 1):
    if S == 0:
        break
    tmp_max = arr[i]
    index = i
    for j in range(i + 1, min(N, i + 1 + S)):
        if tmp_max < arr[j]:
            tmp_max = arr[j]
            index = j
    S -= index - i
    for j in range(index, i, -1):
        arr[j] = arr[j - 1]
    arr[i] = tmp_max
    
print(*arr)