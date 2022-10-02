import sys, heapq
T = int(sys.stdin.readline())
exist = [False for _ in range(1000001)]

def calculate():
    k = int(sys.stdin.readline())
    min_q, max_q = [], []
    for i in range(k):
        operation, num = map(str, sys.stdin.readline().split())
        num = int(num)
        # Input : min_q, max_q 둘 다 heappush
        if operation == 'I':
            exist[i] = True
            heapq.heappush(min_q, (num, i))
            heapq.heappush(max_q, (-num, i))
        # 최솟값 삭제
        elif num == -1:
            while min_q and not exist[min_q[0][1]]:
                heapq.heappop(min_q)
            if min_q:
                exist[min_q[0][1]] = False
                heapq.heappop(min_q)
        # 최댓값 삭제
        else:
            while max_q and not exist[max_q[0][1]]:
                heapq.heappop(max_q)
            if max_q:
                exist[max_q[0][1]] = False
                heapq.heappop(max_q)
    while min_q and not exist[min_q[0][1]]:
        heapq.heappop(min_q)
    while max_q and not exist[max_q[0][1]]:
        heapq.heappop(max_q)
    if not max_q:
        print('EMPTY')
    else:
        print(-max_q[0][0], min_q[0][0])

for i in range(T):
    calculate()