import re
from decimal import Decimal
def solution(word, pages):
    answer = 0
    word = word.lower()
    infos = dict()

    for i, page in enumerate(pages):
        page = page.replace('\n', ' ').lower()
        my = re.findall('<meta property="og:url" content="(\S+)"', page) # 현재 페이지 url
        links = re.findall('<a href="(https://[\S]*)"', page) # 외부 링크 url들
        basic_num = 0
        for f in re.findall(r'[a-zA-Z]+', page): # 기본 점수
            if f == word:
                basic_num += 1
        infos[my[0]] = [i, len(links), basic_num, 0, links] # [인덱스번호, 외부 링크 수, 기본점수, 매칭점수, 링크 url 정보]
    
    answer = -1
    index = -1
    for i in infos: # 링크 점수 구함
        for link in infos[i][4]:
            if link not in infos:
                continue
            infos[link][3] += (infos[i][2] / infos[i][1])
    for i in infos: # 매칭 점수 구해서 가장 큰 인덱스 찾음
        matching = infos[i][2] + infos[i][3]
        if matching > answer:
            answer = matching
            index = infos[i][0]
    return index