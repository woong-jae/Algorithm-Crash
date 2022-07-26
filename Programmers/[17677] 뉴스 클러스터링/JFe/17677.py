from collections import defaultdict
import re
def make_dict(str):
    str_dict = defaultdict(int)
    pattern = re.compile('[A-Z]+')  # 영문 대문자인 경우만
    for i in range(len(str)-1):
        if not pattern.match(str[i]) or not pattern.match(str[i+1]): continue
        str_dict[str[i]+str[i+1]] += 1
    return str_dict

def solution(str1, str2):
    answer, intersect, union = 0, 0, 0
    # 대문자로 대소문자 통일 후, 딕셔너리 생성
    str1_dict = make_dict(str1.upper())
    str2_dict = make_dict(str2.upper())
    # 둘 다 공집합이면 65536 return
    if len(str1_dict) == 0 and len(str2_dict) == 0: return 65536
    # str1 딕셔너리 기준으로 교집합, 차집합 수 계산
    for key in str1_dict.keys():
        # str2 딕셔너리에 값 있으면 교집합 계산
        if str2_dict[key] > 0:
            intersect += min(str1_dict[key], str2_dict[key])
        union += max(str1_dict[key], str2_dict[key])    # 합집합 계산
        # 계산한 key 값 0으로
        str1_dict[key] = 0
        str2_dict[key] = 0
    # str2 딕셔너리 기준으로 교집합, 차집합 수 계산
    for key in str2_dict.keys():
        # 두 딕셔너리 다 0이 아닌 경우, 교집합 계산
        if str1_dict[key] > 0 and str2_dict[key] > 0:
            intersect += min(str1_dict[key], str2_dict[key])
        union += max(str1_dict[key], str2_dict[key])
    answer = intersect / union
    return int(answer*65536)