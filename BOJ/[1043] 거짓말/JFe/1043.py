import sys
from collections import defaultdict

N, M = map(int, sys.stdin.readline().split())
true_list = list(map(int, sys.stdin.readline().split()))
party = [[int(x) for x in sys.stdin.readline().split()] for _ in range(M)]
people_dict = defaultdict(bool)
result = M
# true_list 값들 people_dict 업데이트
for v in true_list[1:]:
    people_dict[v] = True
# 파티 참여 인원 중 진실을 아는 사람이 있으면 해당 파티 인원 전부 people_dict 업데이트
# 업데이트 된 이후에 한 번 더 업데이트 해야 되므로 두 번 반복
for _ in range(M):
    for i in range(M):
        flag = False
        # 진실 아는 사람 있으면 flag를 True로
        for v in party[i][1:]:
            if people_dict[v]:
                flag = True
                break
        # flag가 True면 해당 파티 인원 전부 people_dict 업데이트
        if flag:
            for v in party[i][1:]:
                people_dict[v] = True
# 파티에 진실 아는 사람이 있으면 result --
for i in range(M):
    for v in party[i][1:]:
        if people_dict[v]:
            result -= 1
            break
print(result)