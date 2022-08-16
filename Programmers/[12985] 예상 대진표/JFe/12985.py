import math
def solution(n,a,b):
    answer = 1
    while abs(a-b) != 1 or abs(a//2 - b//2) != 1:
        # 조건 1. a, b의 차가 1이면서 / 조건 2. a, b를 각각 2로 나눴을 때 몫이 1만큼 차이나면 중단
        # (ex. a = 3, b = 4인 경우처럼) (cf. a = 4, b = 5인 경우는 서로 만나지 않음. <- 2번 조건에 해당되지 않음)
        answer += 1
        a, b = math.ceil(a/2), math.ceil(b/2)   # 다음 라운드 번호 계산
    return answer