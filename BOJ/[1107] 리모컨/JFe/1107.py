import sys

N = int(sys.stdin.readline())
M = int(sys.stdin.readline())
button = [int(x) for x in sys.stdin.readline().split()]
result = abs(N - 100)
# 이동 가능한 번호 탐색
for i in range(1000000):
    temp = list(str(i))
    flag = True
    # 고장난 번호가 포함되어 있으면 flag를 False로 바꾸고 break
    for t in temp:
        if int(t) in button:
            flag = False
            break
    # flag가 True면 이동하는데 누르는 횟수(len(temp)) + 이동한 위치에서 목표 위치까지 차이(abs(N - i))와 result 중 최솟값 업데이트
    if flag: result = min(result, abs(N - i) + len(temp))
print(result)