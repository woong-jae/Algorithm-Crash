def solution(str1, str2):
    answer = 0
    str1 = str1.upper()
    str2 = str2.upper()
    set1, set2 = dict(), dict()
    join, sum = 0, 0

    for i in range(len(str1) - 1):
        tmp = str1[i] + str1[i + 1]
        if not tmp.isalpha():
            continue
        sum += 1
        if tmp not in set1:
            set1[tmp] = 1
        else:
            set1[tmp] += 1
        
    for i in range(len(str2) - 1):
        tmp = str2[i] + str2[i + 1]
        if not tmp.isalpha():
            continue
        sum += 1
        if tmp not in set2:
            set2[tmp] = 1
        else:
            set2[tmp] += 1

    if not set1 and not set2:
        answer = 1
    else:
        for i in set1:
            if i in set2:
                join += min(set1[i], set2[i])
        answer = join / (sum - join)  

    return int(answer * 65536)