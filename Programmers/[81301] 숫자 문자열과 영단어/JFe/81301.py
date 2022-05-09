def solution(s):
    answer, word = 0, ''
    number = ['zero', 'one', 'two', 'three', 'four', 'five', 'six', 'seven', 'eight', 'nine']
    # 문자열 s에서 글자 하나씩 분석
    for c in s:
        # 숫자인 경우
        if c.isdigit():
            answer *= 10
            answer += int(c)
        # 숫자가 아닌 경우
        else:
            word += c
        # word가 완성된 경우
        if word in number:
            answer *= 10
            answer += number.index(word)
            word = ''
    return answer