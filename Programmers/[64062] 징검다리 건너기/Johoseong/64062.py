def solution(stones, k):
    answer = 2000000000
    start = min(stones)
    end = max(stones)

    while start <= end:
        mid = (start + end) // 2

        count = 0
        for s in stones: # 연속된 못 밟는 돌 개수 셈
            if s - mid <= 0:
                count += 1
            else:
                count = 0

            if k <= count:
                break

        if k <= count: # 못 밟는 돌 개수가 k보다 큼 (징검다리 못 건너는 경우)
            end = mid - 1
            answer = min(answer, mid)
        else: # 징검다리 건널 수 있는 경우
            start = mid + 1

    return answer

print(solution([2, 4, 5, 3, 2, 1, 4, 2, 5, 1], 3))