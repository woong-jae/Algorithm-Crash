def solution(s):
    answer = [0, 0]
    # s가 1이 될 때까지 이진 변환 반복
    while s != '1':
        answer[0] += 1              # 이진 변환 횟수 추가
        cnt = s.count('0')          # s에 포함된 0 개수
        s = bin(len(s) - cnt)[2:]   # 0을 제외한 길이를 이진수로 변환('0b 제거 [2:]')
        answer[1] += cnt            # 제거된 0 개수 추가
    return answer