# [2800] 괄호 제거 - Python

## 🔍 Algorithm
**스택, 조합**

## 💻 Logic

```Python
# 괄호 인덱스 저장
for i, c in enumerate(s):
    if c == '(': stack.append(i)
    elif c == ')': arr.append([stack.pop(), i])
```
- **`stack`을 이용하여 해당하는 괄호쌍의 인덱스를 저장**  

```Python
# 인덱스별 조합 생성 후 괄호 제거
for i in range(1, len(arr)+1):
    comb = list(combinations(arr, i))
    for c in comb:
        temp = list(s)
        for a, b in c:
            temp[a], temp[b] = '', ''
        answer.add(''.join(temp[:-1]))
for a in sorted(answer): print(a)
```
- **인덱스별 조합 생성 후 괄호 제거**  
    - `combinations`를 이용하여 인덱스별 조합 생성  
    - 해당 조합의 인덱스에 맞는 괄호들을 제거한 후, 제거한 리스트를 다시 문자열로 변환하여 저장  
    - 오름차순 정렬하여 출력  

## 📝 Review

괄호 문제는 저번주에도 풀어봤고, 조합은 평소에도 자주 사용하는 풀이 방식이어서 쉽게 해결할 수 있었지만  
오랜만에 백준 문제를 푸니까 list를 다시 문자열로 변환할 때, `\n`이 들어간다는 사실을 까먹었었다ㅎ;;