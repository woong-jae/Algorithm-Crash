from collections import defaultdict

def solution(gems):
    start = 0
    current = defaultdict(int)  # 기본값 0인 dictionary 생성
    count = len(set(gems))  # set을 이용해서 중복 제거하고 전체 보석 수 저장
    answer = [1, len(gems)]
    # start, end 투 포인터를 이용해서 가장 짧은 구간 찾기
    for end in range(len(gems)):
        current[gems[end]] += 1
        # 모든 보석을 하나 이상 포함하는 경우
        while len(current) == count:
            # 해당 구간의 길이가 answer 구간 길이보다 작으면 업데이트
            if end - start < answer[1] - answer[0]:
                answer[0], answer[1] = start+1, end+1
            current[gems[start]] -= 1
            # 해당 키 값이 0이 되면 dictionary에서 삭제
            if current[gems[start]] == 0:
                del current[gems[start]]
            start += 1
    return answer