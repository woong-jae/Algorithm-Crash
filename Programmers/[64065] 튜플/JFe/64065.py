def solution(s):
    answer = []
    s_list = s.split('},{')     # '},{' 기준으로 문자열 나눔
    for i in range(len(s_list)):
        s_list[i] = s_list[i].strip('{''}') # 문자열 양 끝마다 필요없는 괄호 제거
        s_list[i] = list(map(int, s_list[i].split(',')))    # ','를 기준으로 나누고 int로 변환해서 리스트 저장
    s_list.sort(key=len)    # 길이 순서로 오름차순 정렬
    # 리스트 반복문 돌면서 answer에 없는 값이면 answer에 추가 
    for l in s_list:
        for n in l:
            if n not in answer: answer.append(n)
    return answer