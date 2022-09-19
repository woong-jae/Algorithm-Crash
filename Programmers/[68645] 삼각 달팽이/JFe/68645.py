def solution(n):
    answer, x, y, count, d =  [], 0, -1, 1, 0
    field = [[0 for _ in range(n)] for _ in range(n)]
    # n, n-1, n-2, ... 만큼씩 반복 횟수 줄이면서 d 방향대로 count 증가시키면서 대입
    for i in reversed(range(1, n + 1)):
        for j in range(i):
            # d 방향에 따라 다음 위치 계산
            if d == 0: y += 1
            elif d == 1: x += 1
            else: x, y = x - 1, y - 1
            field[y][x] = count
            count += 1
        # 정해진 반복 횟수만큼 수행했으면, d 방향 바꾸기
        d = d + 1 if d < 2 else 0
    # field 값이 0이 아닌 경우만 순서대로 출력
    for i in range(n):
        for j in range(n):
            if field[i][j] == 0: break
            answer.append(field[i][j])
    return answer