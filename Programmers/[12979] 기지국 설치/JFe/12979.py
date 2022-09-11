import math
def solution(n, stations, w):
    answer, start, end = 0, 1, 1
    block = w * 2 + 1
    # 1. station 전파가 도달하는 제일 앞 부분을 end로 설정
    # 2. (end - start) 거리를 block으로 나눈 값을 올림한 만큼 기지국 추가 설치 가능
    # 3. 다음 start 위치를 현재 station 전파가 도달하는 제일 끝 부분 다음으로 설정
    for v in stations:
        end = v - w
        answer += math.ceil((end - start) / block)
        start = v + w + 1
    # start가 끝에 도달하지 않은 경우, 추가로 진행
    if start <= n:
        answer += math.ceil((n + 1 - start) / block)
    return answer