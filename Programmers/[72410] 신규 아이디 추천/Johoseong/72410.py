import re
def solution(new_id):
    # 1단계
    id1 = new_id.lower()
    # 2단계
    id2 = re.sub('[^a-z0-9-_.]', '', id1)
    # 3단계
    tmp = id2.split('.')
    id3 = '.'
    for word in tmp:
        if not word: continue
        id3 += (word + '.')
    # 4단계
    if id3[0] == '.': id3 = id3[1:]
    if id3 and id3[-1] == '.': id3 = id3[:-1]
    # 5단계
    if not id3: id3 = 'a'
    # 6단계
    if len(id3) >= 16:
        id3 = id3[:15]
        if id3[-1] == '.': id3 = id3[:-1]

    # 7단계
    if len(id3) <= 2:
        last = id3[-1]
        id3 += (last * (3 - len(id3)))

    return id3