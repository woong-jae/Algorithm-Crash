import copy
from itertools import permutations

def calculate(op, num, order):
    for o in order:
        temp_num = [num[0]]
        temp_op = []
        for i, v in enumerate(op):
            # 연산자가 우선순위 아닌 경우
            if v != o: 
                temp_num.append(num[i+1])
                temp_op.append(v)
                continue
            # 연산자가 우선순위인 경우, 연산자에 따라 계산
            if v == '+': temp_num.append(temp_num.pop() + num[i+1])
            elif v == '-': temp_num.append(temp_num.pop() - num[i+1])
            else: temp_num.append(temp_num.pop() * num[i+1])
        num = temp_num
        op = temp_op
    return num[0]

def solution(expression):
    answer, temp, last = 0, 0, 0
    op, num = [], []
    # 숫자, 연산자 분리
    for e in expression:
        if e == '*' or e == '+' or e == '-':
            op.append(e)
            num.append(temp)
            temp = 0
        else:
            temp = 10 * temp
            temp += int(e)
    num.append(temp)
    # 순열 생성
    order_list = permutations(set(op))  # op 중복 제거하고 순열 생성
    for order in order_list:
        result = abs(calculate(op, num, order)) # 계산 결과 절댓값으로 저장
        answer = max(answer, result)    # 순열 반복하면서 최댓값 저장
    return answer