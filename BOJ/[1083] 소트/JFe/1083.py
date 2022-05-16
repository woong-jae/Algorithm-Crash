import sys
N = int(sys.stdin.readline())
A = [int(x) for x in sys.stdin.readline().split()]
S = int(sys.stdin.readline())
# S번 교환하면서 가장 내림차순이 되게 만들기
for i in range(N):
    max_idx, max_v = i, A[i]
    # 현재 위치에서 최대로 교환 가능한 위치까지 최댓값 구하기
    for j in range(i, i+S+1):
        if j == N: break
        # 최댓값, 최댓값 인덱스 저장
        if max_v < A[j]:
            max_v = A[j]
            max_idx = j
    # 최댓값 위치까지 교환
    for j in range(max_idx, i, -1):
        A[j], A[j-1] = A[j-1], A[j]
        S -= 1
    if S == 0: break    # 더 이상 교환 못하면 break
for i in A: print(i, end=' ')