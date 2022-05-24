import re
from itertools import permutations
def solution(user_id, banned_id):
    result = []
    order_list = permutations(user_id, len(banned_id))  # 순열 생성
    for order in order_list:
        check = True
        for i, id in enumerate(banned_id):
            # 정규 표현식 사용 (^: 시작 부분 매치 확인, $: 끝 부분 매치 확인)
            pattern = re.compile(f"^{id.replace('*', '.')}$")   # '*'는 '.'으로 치환
            if not pattern.match(order[i]): check = False
        # 전부 다 일치하는 경우
        if check:
            order = sorted(list(order)) # 중복 제거를 위해 우선 정렬
            result.append(tuple(order)) # set 처리하기 위해 tuple로 변환 후 append
    return len(set(result)) # set 이용해서 중복 제거