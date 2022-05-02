def solution(gems):
    answer = []
    gem_dict = dict()
    gem_unique = len(set(gems))
    start = 0
    end = 0
    section = len(gems) + 1 # 결과 진열대 범위 (gems 길이보다 길 순 없으니까 이렇게 초기화)

    while end < len(gems):
        if gems[end] not in gem_dict: # 발견 안된 보석이면 딕셔너리에 추가
            gem_dict[gems[end]] = 1
        else: # 이미 발견한 적 있는거면 발견 횟수 증가
            gem_dict[gems[end]] += 1
        
        end += 1 # 끝점 한칸 이동

        if len(gem_dict) == gem_unique: # 보석 종류별로 다 찾았으면?
            while start < end: # 더 짧은 범위인지 검사함
                if gem_dict[gems[start]] > 1: # 한번보다 더 발견됐다 = 뒤에 더 있다
                    gem_dict[gems[start]] -= 1 # 시작점 이동할 거니까 발견 값 하나 빼줌
                    start += 1 # 시작점 한칸 이동
                elif (end - start) < section: # 이렇게 갱신한 범위가 이전에 찾은 범위보다 작으면?
                    section = end - start 
                    answer = [start + 1, end] # 범위 갱신
                    break
                else: break

    return answer