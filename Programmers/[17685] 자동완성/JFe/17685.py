from collections import defaultdict
def solution(words):
    answer = 0
    length = defaultdict(int)
    words.sort()
    for i in range(len(words)-1):
        count = 0
        # 뒷 단어와 공통된 문자 수 체크
        for j in range(len(words[i])):
            if words[i][j] != words[i+1][j]: break
            count += 1
        # 원래 단어 수와 같으면 count 그대로
        if count == len(words[i]):
            length[i] = max(length[i], count)
        # 원래 단어 수와 다르면 count + 1
        else:
            length[i] = max(length[i], count+1)
        # 원래 단어 수와 같으면 count 그대로
        if count == len(words[i+1]):
            length[i+1] = count
        # 원래 단어 수와 다르면 count + 1
        else:
            length[i+1] = count+1
    return sum(length.values())