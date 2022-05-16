# [64064] 불량 사용자
## Algorithm
- 문자열, 비트마스크
## Logic
1. 각 불량 사용자 id에 대해서, 될 수 있는 유저 id의 인덱스 ```ban_list```에 구함
```python
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
```
2. 재귀 돌면서 가능한 제재 id 조합을 셈. (단, 이미 count한 경우 중복 발생 안하도록 bitmask 씀)
```python
def find_set(cur, set, n):
    if cur == n:
        visit[set] = 1 # 조합 하나 생성
        return

    for i in ban_list[cur]:
        if set & (1 << i): # 이미 제재된 유저 id면 넘김
            continue
        find_set(cur + 1, set | (1 << i), n)
```
- 시간복잡도 O(N^2)

## Review
그냥 재귀로 제재유저 구하려고 하니까 1234랑 1243을 다른 케이스로 인식해서 결국 비트마스크 썼다. 순서가 중요해서 처음부터 순열 구한 다음에 불량 사용자 매칭해도 풀 수 있을듯?
