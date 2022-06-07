from string import ascii_uppercase
from collections import defaultdict
def solution(msg):
    answer, cur = [], 0
    dic = defaultdict(int)
    word = msg[cur]
    # 영문 대문자 딕셔너리 생성
    for i, v in enumerate(ascii_uppercase):
        dic[v] = i + 1
    # 문자 딕셔너리 검사
    while cur <= len(msg):
        # 딕셔너리에 없으면 추가 & 이전 값 answer에 추가
        if not dic[word]:
            dic[word] = len(dic)
            answer.append(dic[word[:-1]])
            word = msg[cur]
            continue
        # 끝까지 가면 answer 추가 후 종료
        if cur + 1 == len(msg):
            answer.append(dic[word])
            break
        cur += 1
        word += msg[cur]
    return answer