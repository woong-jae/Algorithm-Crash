import sys

exp = sys.stdin.readline().rstrip()
postfix, stack = '', []
for e in exp:
    # 알파벳인 경우 (피연산자인 경우)
    if e.isalpha():
        postfix += e
        continue
    # * or / 인 경우 : * or / 아닌 값이 나올 때까지 pop한 후, append
    if e == '*' or e == '/':
        while stack:
            if stack[-1] != '*' and stack[-1] != '/': break
            postfix += stack.pop()
        stack.append(e)
    # + or - 인 경우 : ( 아닌 값이 나올 때까지 pop한 후, append
    elif e == '+' or e == '-':
        while stack:
            if stack[-1] == '(': break
            postfix += stack.pop()
        stack.append(e)
    # ( 인 경우 : 바로 append
    elif e == '(':
        stack.append(e)
    # ) 인 경우 : (가 나올 때까지 pop한 후, (도 pop
    else:
        while stack:
            if stack[-1] == '(': 
                stack.pop()
                break
            postfix += stack.pop()
while stack:
    postfix += stack.pop()
print(postfix)