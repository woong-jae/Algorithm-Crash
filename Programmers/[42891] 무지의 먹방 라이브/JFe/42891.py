import heapq
def solution(food_times, k):
    h, total_time, pre_time, answer = [], 0, 0, 0
    # k 시간에 섭취할 음식이 없는 경우
    if sum(food_times) <= k: return -1
    # 우선순위 큐 생성
    for i, time in enumerate(food_times):
        heapq.heappush(h, [time, i])
    # 가장 작은 양의 음식부터 먹기 (k 시간을 넘지 않을 때까지만 반복)
    while total_time + (h[0][0] - pre_time) * len(h) <= k:
        cur_time = heapq.heappop(h)[0]
        total_time += (cur_time - pre_time) * (len(h)+1)
        pre_time = cur_time
    # 음식 번호 순으로 정렬
    sorted_list = sorted(h, key = lambda x : x[1])
    answer = sorted_list[(k-total_time)%len(h)][1] + 1
    return answer