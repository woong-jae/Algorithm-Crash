import sys  
sys.setrecursionlimit(10**8)

def solution(p):
    # 올바른 괄호 문자열인지 확인
    def check_correct(s):
        left, right = 0, 0
        for i, c in enumerate(s):
            if c == '(':
                left += 1
            else:
                right += 1
            if left < right:
                return False
        return True
    # 2. 문자열 u, v로 분리 (분리하는 기준 인덱스 반환)
    def separate(s):
        left, right = 0, 0
        for i, c in enumerate(s):
            if c == '(':
                left += 1
            else:
                right += 1
            if left > 0 and right > 0 and left == right:
                return i
    # 4-4. u 문자열 변환
    def reverse(s):
        temp = s[1:-1]
        temp = temp.replace('(', '-').replace(')', '(').replace('-', ')')
        return temp
    # 전체 재귀 함수
    def recursion(s):
        if len(s) == 0: return ''   # 1. 빈 문자열인 경우
        index = separate(s)
        u, v = s[:index+1], s[index+1:]
        # 3. u가 올바른 괄호 문자열인 경우
        if check_correct(u):
            return u + recursion(v)
        # 4. u가 올바른 괄호 문자열이 아닌 경우
        else:
            temp = reverse(u)
            return '(' + recursion(v) + ')' + temp
            
    return recursion(p)