import heapq
def solution(food_times, k):
    N = len(food_times)
    if sum(food_times) <= k:
        return -1

    heap = []
    for i in range(N):
        heapq.heappush(heap, [food_times[i], i + 1])

    time, pre = 0, 0
    while time + (heap[0][0] - pre) * N <= k:
        cur = heapq.heappop(heap)
        time += (cur[0] - pre) * N
        N -=1
        pre = cur[0]
    answer = sorted(heap, key=lambda x:x[1])

    return answer[(k - time) % N][1]