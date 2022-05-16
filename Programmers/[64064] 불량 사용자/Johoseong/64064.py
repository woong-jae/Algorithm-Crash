def find_set(cur, set, n):
    if cur == n:
        visit[set] = 1 # 조합 하나 생성
        return

    for i in ban_list[cur]:
        if set & (1 << i): # 이미 제재된 유저 id면 넘김
            continue
        find_set(cur + 1, set | (1 << i), n)

def solution(user_id, banned_id):
    global visit, ban_list
    answer = 0
    visit = [0] * (1 << 8)
    ban_list = [[] for _ in range(len(banned_id))]

    for i, ban in enumerate(banned_id): # 각 불량 사용자 id에 해당하는 유저 id 찾음
        for j, user in enumerate(user_id):
            if len(user) != len(ban):
                continue

            flag = 1
            for k in range(len(ban)):
                if ban[k] != '*' and user[k] != ban[k]:
                    flag = 0
                    break

            if flag == 1:
                ban_list[i].append(j)

    find_set(0, 0, len(banned_id)) # 조합 찾음

    for i in visit:
        if i == 1:
            answer += 1

    return answer