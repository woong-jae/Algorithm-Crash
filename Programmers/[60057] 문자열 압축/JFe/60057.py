import sys
def solution(s):
    answer = sys.maxsize
    # (1개 ~ len(s)//2+1개) 단위까지 반복
    for i in range(1, len(s)//2 + 2):
        index, dup, temp = i, 1, ''
        cur, last = s[0:i], s[0:i]
        while index < len(s):
            cur = s[index:index+i]
            index += i
            # 전 문자열과 같으면, dup 증가
            if cur == last:
                dup += 1
                continue
            # 전 문자열과 다르면,
            # dup가 1이상이면 dup값 문자열로 추가
            if dup > 1:
                temp += str(dup)
                dup = 1
            temp += last
            last = cur
        # 반복문 끝난 뒤, 값 추가
        if dup > 1:
            temp += str(dup) + last
        else:
            temp += cur
        # 최솟값 저장
        answer = min(answer, len(temp))
    return answer