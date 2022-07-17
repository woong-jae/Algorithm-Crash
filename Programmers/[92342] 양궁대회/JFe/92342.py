from itertools import combinations_with_replacement

def solution(n, info):
    answer = [-1 for _ in range(11)]
    max_score = 0
    # 중복 조합으로 경우의 수 계산
    for comb in combinations_with_replacement(range(11), n):
        arrow = [0 for _ in range(11)]
        score = 0
        for c in comb:
            arrow[c] += 1
        for i in range(11):
            # 라이언 화살 수가 더 많으면 점수 추가
            if arrow[i] > info[i]:
                score += (10 - i)
            # 점수 차이가 크도록 해야 하기 때문에 어피치 점수 제거
            elif info[i] != 0:
                score -= (10 - i)
        if score <= 0: continue # 라이언 점수가 더 낮거나 같은 경우
        # 점수 차이가 같으면 가장 낮은 점수를 더 많이 맞힌 경우로
        if score == max_score and arrow[::-1] > answer[::-1]:
            max_score = score
            answer = arrow[:]
        # 점수 차이가 큰 경우 업데이트
        elif score > max_score:
            max_score = score
            answer = arrow[:]
    if answer[0] == -1: return [-1]
    return answer