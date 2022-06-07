import re
def solution(files):
    answer, file_list = [], []
    for i, file in enumerate(files):
        # head 매치
        pattern = re.compile('[^0-9]+')
        m = pattern.match(file)
        head = m.group().lower()
        # number 매치
        pattern = re.compile('[0-9]+')
        m = pattern.match(file[m.end():])
        number = int(m.group())
        # file_list에 저장
        file_list.append([i, head, number, file])
    # head, number, index 순으로 정렬
    file_list = sorted(file_list, key = lambda x: (x[1], x[2], x[0]))
    # 정렬 된 순서대로 answer에 추가
    for i in range(len(file_list)):
        answer.append(file_list[i][3])
    return answer