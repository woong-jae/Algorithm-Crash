def solution(gems):
    answer = []
    unique = dict()
    gem_len = len(gems)
    end = 0
    start = 0
    tmp = 100000

    gem_cnt = 0
    for gem in gems: # 보석 종류 파악
        if gem not in unique:
            unique[gem] = gem_cnt
            gem_cnt += 1

    matrix = [[-1] * len(unique) for i in range(gem_len)]

    for i, gem in enumerate(gems):
        if i > 0: # 이전 기록
            matrix[i] = matrix[i - 1]

        col = unique[gem]
        matrix[i][col] = i  # 해당 보석 위치로 인덱스 갱신
        
        if min(matrix[i]) != -1: # 길이 제일 짧은거로 answer 갱신
            start = min(matrix[i])
            end = max(matrix[i])
            if end - start < tmp:
                tmp = end - start
                answer = [start + 1, end + 1]

    return answer

print(solution(["DIA", "RUBY", "RUBY", "DIA", "DIA", "EMERALD", "SAPPHIRE", "DIA"]))