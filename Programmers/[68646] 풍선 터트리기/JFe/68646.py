import heapq
def solution(a):
    answer, left, right = 2, [], []
    heapq.heappush(left, a[0])
    for i in reversed(range(2, len(a))):
        heapq.heappush(right, (a[i], i))
    # 풍선 하나씩 터트릴 수 있는지 탐색
    for i in range(1, len(a)-1):
        # right 값 중에 이미 지나간 인덱스면 pop
        while i > right[0][1]:
            heapq.heappop(right)
        left_temp, right_temp = left[0], right[0]
        heapq.heappush(left, a[i])  # left에 값 추가
        # left 중 최솟값과 right 중 최솟값 둘 다 현재 기준 값(a[i])보다 작으면 continue
        if a[i] > left_temp and a[i] > right_temp[0]: continue
        answer += 1
    return answer