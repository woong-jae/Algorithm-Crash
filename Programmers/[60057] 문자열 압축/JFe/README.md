# [60057] 문자열 압축 - Python

## 🔍 Algorithm
**Brute Force**

## 💻 Logic

```Python
# (1개 ~ len(s)//2+1개) 단위까지 반복
    for i in range(1, len(s)//2 + 2):
        index, dup, temp = i, 1, ''
        cur, last = s[0:i], s[0:i]
        while index < len(s):
            cur = s[index:index+i]
            index += i
            # 전 문자열과 같으면, dup 증가
            if cur == last:
                dup += 1
                continue
            # 전 문자열과 다르면,
            # dup가 1이상이면 dup값 문자열로 추가
            if dup > 1:
                temp += str(dup)
                dup = 1
            temp += last
            last = cur
        # 반복문 끝난 뒤, 값 추가
        if dup > 1:
            temp += str(dup) + last
        else:
            temp += cur
        # 최솟값 저장
        answer = min(answer, len(temp))
```
- **(1개 ~ len(s)//2+1개) 단위까지 반복**  
    - **전 문자열과 같으면**, `dup` 증가   
    - **전 문자열과 다르면**, `last` 값 추가 & `last`를 `cur` 값으로 변경  
      (이 때, `dup`가 **1이상**이면 `dup`값 문자열로 추가)  
    - 반복문 끝난 뒤에도 `dup` 여부에 따라 값 추가  
    - `answer`에 최솟값 저장  



## 📝 Review

처음에 문제를 잘 이해를 못해서 시간이 걸렸다,,  
구현하고 나서도 테스트케이스 하나가 해결이 안됐었는데 len(s)//2+1개까지 돌려야 하는 걸 몰랐음,,  
문제도 마음에 안들고 내 풀이도 마음에 안들었던 문제,,  
