from collections import defaultdict
def solution(record):
    answer, log = [], []
    state = defaultdict(bool)   # 유저별 현재 채팅방에 있는지 여부
    nick = defaultdict(str)     # 유저별 닉네임 현황
    # record -> dictionay 처리
    for r in record:
        temp = r.split(" ")
        if temp[0] == "Enter":
            state[temp[1]] = True
            nick[temp[1]] = temp[2]
            log.append([temp[0], temp[1]])
        elif temp[0] == "Leave":
            state[temp[1]] = False
            log.append([temp[0], temp[1]])
        else:
            nick[temp[1]] = temp[2]
    # 최종 메세지 출력
    for command, id in log:
        if command == "Enter":
            answer.append(nick[id] + "님이 들어왔습니다.")
        else:
            answer.append(nick[id] + "님이 나갔습니다.")
    return answer