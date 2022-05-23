def make_dict(s, set):
    global sum
    for i in range(len(s) - 1):
        tmp = s[i] + s[i + 1]
        if not tmp.isalpha():
            continue
        sum += 1
        if tmp not in set:
            set[tmp] = 1
        else:
            set[tmp] += 1
            
def solution(str1, str2):
    global sum
    answer = 0
    str1, str2 = str1.upper(), str2.upper()
    set1, set2 = dict(), dict()
    join, sum = 0, 0

    make_dict(str1, set1) # 두 글자씩 끊어서 딕셔너리에 횟수를 key로 저장
    make_dict(str2, set2)

    if not set1 and not set2:
        answer = 1
    else: # set1의 원소가 set2에도 있으면 (= 교집합 원소), join에 개수 더함
        for i in set1:
            if i in set2:
                join += min(set1[i], set2[i])
        answer = join / (sum - join) # 합집합 = 집합1 + 집합2 - 교집합

    return int(answer * 65536)