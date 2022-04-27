# [67257] 수식 최대화 - Python

## 🔍 Algorithm
**Permutation, Stack**

## 💻 Logic

```Python
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
```
- **숫자, 연산자 분리**  
  연산자를 만나기 전까지의 값들은 숫자 형태로 `temp`에 계산해서 저장해두고  
  연산자를 만나면 연산자를 `op`에 저장된 숫자 `temp`를 `num`에 **append**  

```Python
# 순열 생성
order_list = permutations(set(op))  # op 중복 제거하고 순열 생성
for order in order_list:
    result = abs(calculate(op, num, order)) # 계산 결과 절댓값으로 저장
    answer = max(answer, result)    # 순열 반복하면서 최댓값 저장
```
- **순열 생성**  
  연산자 우선순위를 정하기 위해 **permutations**를 이용해서 순열 생성  
  이 때, `op`에 중복을 제거하기 위해 **set** 사용  
  순열을 반복문 돌면서 우선순위에 맞게 `calculate` 함수 호출해서 계산하고 결과값을 절댓값으로 `result`에 저장  
  각 우선순위마다 나오는 결과값들 중 최댓값을 `answer`에 저장  

```Python
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
```
- **정해진 우선순위 `order`에 맞게 계산하는 함수**  
  정해진 우선순위를 반복문 돌면서 `op` 리스트에서 해당 연산자가 우선순위인지 아닌지에 나눠서 처리  
  **해당 연산자가 우선순위가 아닌 경우** -> 그 연산자 뒤에 오는 숫자를 `temp_num`에, 해당 연산자를 `temp_op`에 **append**  
  **해당 연산자가 우선순위인 경우** -> `temp_num` 스택에서 **pop**한 값과 연산자 뒤에 오는 숫자를 연산자에 맞게 계산하고 `temp_num`에 다시 **append**  
  

## 📝 Review

연산자 우선순위를 정해야 한다고 해서 `permutations`를 이용해서 순열로 만들어야겠다고 생각하고 구현했다.  
처음에는 반복문을 돌면서 인덱스를 이용해 리스트에서 값을 삭제하고 계산한 값을 넣는 방식으로 계산하는 부분을 구현하려고 했는데,  
인덱스 에러가 나서 스택을 이용해 계산하는 방식으로 바꿨다.  

