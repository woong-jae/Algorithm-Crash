# [67257] 수식 최대화
## Algorithm
- stack
## Logic
1. 연산자들의 우선순위 경우의 수를 permutation으로 6가지 구함 (연산자가 '*', '-', '+' 3개만 있어서)
2. 주어진 문자열 -> 정수와 연산자로 분리해서 배열에 넣음
3. 6가지 우선순위 경우의 수에 따라서 수식 계산함
- 우선순위 높은 연산자 순으로 계산해나감
- 수식 차례대로 탐색하면서 현재 계산해야되는 연산자 만남 -> 해당 연산자 앞, 뒤 정수로 연산
- 연산자 뒤의 정수는 연산에 이미 사용됐으므로 stack에 넣으면 안됨. flag 사용 이유

```python
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

```

4. 6개 결과에서 절대값 제일 큰 게 answer
- 우선순위 경우의 수 6, 탐색하는 연산자 수 3이라서 3중 for문이지만 시간 복잡도는 O(N)

## Review
처음에 우선순위 따라서 postfix로 변환하고 계산했는데 안되는 경우들 있었다. 그래서 지금처럼 그냥 우선순위 별로 계산해버리게 바꾸니까 됐다. 그런데 계산 구현이 flag 때문에 더러운데 그냥 제출했다..
