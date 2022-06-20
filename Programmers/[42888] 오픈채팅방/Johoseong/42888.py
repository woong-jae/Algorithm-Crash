def solution(record):
    answer = []
    result = []
    str_enter = "님이 들어왔습니다."
    str_leave = "님이 나갔습니다."
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

    for a in answer:
        if a[1] == 0: result.append(user_dict[a[0]] + str_enter)
        else: result.append(user_dict[a[0]] + str_leave)

    return result