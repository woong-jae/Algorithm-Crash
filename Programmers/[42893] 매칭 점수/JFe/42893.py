import re
from collections import defaultdict

def solution(word, pages):
    basic_score = defaultdict(int)      # 기본 점수
    external_num = defaultdict(int)     # 외부 링크 수
    matching_score = defaultdict(int)   # 매칭 점수 [매칭 점수, index] 형태
    link = defaultdict(list)            # 해당 페이지에 링크 걸린 페이지 목록
    page_idx = defaultdict(int)         # 페이지별 인덱스 저장
    for i, page in enumerate(pages):
        # url 찾기
        url = re.search('(<meta property="og:url" content=")(https://\S*)"/>', page).group(2)
        page_idx[url] = i
        # 외부 링크 찾기
        external = re.findall('(<a href=")(https://\S*)">', page)
        for l in external:
            external_num[url] += 1
            link[l[1]].append(url)
        # 페이지에서 단어 찾기
        all_word = re.findall('[a-z]+', page.lower())
        for w in all_word:
            # 단어가 같으면 기본 점수 +
            if w == word.lower():
                basic_score[url] += 1
    # 매칭 점수 계산
    for key, value in page_idx.items():
        temp = 0
        # 링크 점수 계산
        for v in link[key]:
            temp += basic_score[v] / external_num[v]
        matching_score[key] = [temp + basic_score[key], value]  # 매칭 점수 [매칭 점수, index] 형태
    # 매칭 점수 내림차순, index 오름차순 정렬
    sorted_list = sorted(matching_score.values(), key=lambda x:(-x[0],x[1]))
    return sorted_list[0][1]