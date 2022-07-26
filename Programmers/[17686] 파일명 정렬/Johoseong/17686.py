import re

def solution(files):
    answer = []
    N = len(files)
    head = dict()
    number = dict()
    sames = dict()

    for i, f in enumerate(files):
        tmp1 = re.findall('[^0-9]+', f)
        tmp2 = re.findall(r'\d+', f)
        h = tmp1[0].upper()
        head[i] = h
        number[i] = int(tmp2[0])
        sames.setdefault(h, [])
        sames[h].append(i)
        
    head = sorted(head.items(), key=lambda x:x[1])
    for h in head:
        same_head = sames[h[1]]
        if files[h[0]] in answer:
            continue

        if len(same_head) > 1:
            tmp = dict()
            for i in same_head:
                tmp[i] = number[i]
            tmp = sorted(tmp.items(), key=lambda x:x[1])
            for i in tmp:
                answer.append(files[i[0]])
        else:
            answer.append(files[same_head[0]])

    return answer