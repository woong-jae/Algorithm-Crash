from collections import defaultdict

def solution(k, room_number):
    answer = []
    dp = defaultdict(int)   # 원하는 방에 대해 갈 수 있는 다음 방 번호 메모이제이션 (0이면 비어있다는 뜻)
    # 한 명씩 빈 방 찾아서 배정
    for num in room_number:
        temp = []
        next_num = num
        # 빈 방을 찾을 때까지 탐색
        while dp[next_num] > 0:
            next_num = dp[next_num]
            temp.append(next_num)   # 나중에 dp 한번에 업데이트 하기 위해 따로 저장
        # 빈 방 다음 위치로 dp 업데이트
        dp[next_num] = next_num + 1
        for i in temp:
            dp[i] = next_num + 1
        # 빈 방으로 배정
        answer.append(next_num)
    return answer