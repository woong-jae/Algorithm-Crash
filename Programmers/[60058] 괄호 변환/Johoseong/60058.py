def is_correct(p):
    stack = []
    for i in p:
        if i == '(': 
            stack.append(i)
        else:
            if not stack: return False 
            stack.pop()
    return True
    
def convert(u):
    tmp = ''
    for l in u:
        if l == '(': tmp += ')'
        else: tmp += '('
    return tmp

def divide(p):
    left, right = 0, 0
    for i in p:
        if i == '(': left += 1
        else: right += 1
        if left == right: # 괄호 세트 찾았으면 멈춤
            break
    return p[:(left + right)], p[(left + right):]

def solution(p):
    answer = ''
    while len(p) != 0:
        u, p = divide(p)
        if is_correct(u):
            answer += u
        else:
            answer += ('(' + solution(p) + ')' + convert(u[1:-1]))
            break
    return answer

print(solution("(()())()"))