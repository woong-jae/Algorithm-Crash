# [17687] 오픈채팅방
## Algorithm
- 구현

## Logic
- dictionary를 이용해서 유저 id 별로 닉네임을 갱신해준 후, 최종 닉네임에 따라 결과 출력해주면 됨.
1. 유저 id 별 닉네임 갱신. enter와 leave 액션은 유저 id와 함께 저장해둠
```python
user_dict = dict()
for r in record:
    tmp = r.split(" ")
    word, uid = tmp[0], tmp[1]
    if word == "Enter":
        answer.append([str(uid), 0])
        user_dict[uid] = tmp[2]
    elif word == "Leave":
        answer.append([str(uid), 1])
    else:
        user_dict[uid] = tmp[2]
```
2. 저장해둔 액션들은 유저 id의 닉네임을 붙여서 문구와 함께 출력
```python
for a in answer:
    if a[1] == 0: result.append(user_dict[a[0]] + str_enter)
    else: result.append(user_dict[a[0]] + str_leave)
```

## Review
쉬운 문제!