def solution(stones, k):
    answer = 0
    start, end = 0, max(stones)
    # Binary Search
    while start <= end:
        mid = (start + end) // 2
        count = 0
        # 디딤돌 확인
        for s in stones:
            # 밟을 수 있는 경우
            if s >= mid:
                count = 0
                continue
            # 밟을 수 없는 경우
            count += 1
            # 건너뛰어야 하는 디딤돌이 k인 경우 (징검다리를 건널 수 없는 경우)
            if count == k:
                end = mid - 1
                break
        # 징검다리를 건널 수 있는 경우
        if count < k:
            answer = mid
            start = mid + 1
    return answer