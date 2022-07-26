# [17684] 압축 - Python

## 🔍 Algorithm
**문자열**

## 💻 Logic

```Python
# 영문 대문자 딕셔너리 생성
    for i, v in enumerate(ascii_uppercase):
        dic[v] = i + 1
```
- **영문 대문자 딕셔너리 생성**  
    ascii_uppercase를 사용해서 영문 대문자로 이루어진 딕셔너리 생성  

```Python
# 문자 딕셔너리 검사
    while cur <= len(msg):
        # 딕셔너리에 없으면 추가 & 이전 값 answer에 추가
        if not dic[word]:
            dic[word] = len(dic)
            answer.append(dic[word[:-1]])
            word = msg[cur]
            continue
        # 끝까지 가면 answer 추가 후 종료
        if cur + 1 == len(msg):
            answer.append(dic[word])
            break
        cur += 1
        word += msg[cur]
```
- **딕셔너리에 없으면 추가 & 이전 값 answer에 추가**  
- **끝까지 가면 answer 추가 후 종료**  


## 📝 Review

문제에서부터 딕셔너리를 사용하라고 알려주어서 바로 딕셔너리를 사용해서 구현했다.  
영문 대문자 순서대로 딕셔너리를 생성하는 과정에서 `ascii_uppercase`를 사용하면 더 깔끔하게 구현할 수 있어서 변경.  
