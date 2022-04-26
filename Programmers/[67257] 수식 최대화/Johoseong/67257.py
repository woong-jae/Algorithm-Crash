import itertools

def operation(a, b, o):
    if o == '*':
        return (a * b)
    elif o == '+':
        return (a + b)
    return (a - b)

def calculate(expression, operator):
    flag = 0
    for o in operator:
        stack = []
        for i, p in enumerate(expression):
            if p == o:
                a = int(stack.pop())
                b = int(expression[i + 1])
                stack.append(operation(a, b, o))
                flag = 1
            else: 
                if flag == 1:
                    flag = 0
                    continue
                stack.append(p)
        expression = stack
    return stack[0]

def solution(expression):
    answer = 0
    operators = itertools.permutations(['*', '+', '-']) # 식 우선
    parsing = []
    num = ''

    # 문자열 -> 정수, 연산자로 분리
    for e in expression:
        if e not in ['*', '+', '-']:
            num += e
        else:
            parsing.append(num)
            num = ''
            parsing.append(e)
    parsing.append(num)

    for operator in operators:
        # 우선순위 높은 순으로 게산
        result = calculate(parsing, operator)

        if answer < abs(result):
            answer = abs(result)

    return answer

print(solution("50*6-3*2"))