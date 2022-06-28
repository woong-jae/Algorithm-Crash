def check(answer):
    for x, y, a in answer:
        # 기둥인 경우
        if a == 0:
            # 바닥 위 or 보의 한쪽 끝 부분 위 or 다른 기둥 위인지 확인
            if y == 0 or [x-1, y, 1] in answer or [x, y, 1] in answer or [x, y-1, 0] in answer: continue
            return False
        # 보인 경우
        if a == 1:
            # 한쪽 끝 부분이 기둥 위 or 양쪽 끝 부분이 다른 보와 동시에 연결 상태인지 확인
            if [x, y-1, 0] in answer or [x+1, y-1, 0] in answer or [x-1, y, 1] in answer and [x+1, y, 1] in answer: continue
            return False
    return True
    
def solution(n, build_frame):
    answer = []
    for x, y, a, b in build_frame:
        # 설치하는 경우
        if b == 1:
            answer.append([x, y, a])
            if not check(answer):
                answer.remove([x, y, a])
        # 삭제하는 경우
        elif b == 0:
            answer.remove([x, y, a])
            if not check(answer):
                answer.append([x, y, a])
    answer.sort(key = lambda x : (x[0], x[1], x[2]))
    return answer