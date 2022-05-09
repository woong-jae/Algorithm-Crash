# [81301] 숫자 문자열과 영단어 - Python

## 🔍 Algorithm
**문자열**

## 💻 Logic

```Python
# 문자열 s에서 글자 하나씩 분석
    for c in s:
        # 숫자인 경우
        if c.isdigit():
            answer *= 10
            answer += int(c)
        # 숫자가 아닌 경우
        else:
            word += c
        # word가 완성된 경우
        if word in number:
            answer *= 10
            answer += number.index(word)
            word = ''
    return answer
```
- **숫자인 경우 or 아닌 경우**  
  숫자인 경우에는 기존 `answer` 값에 **10**을 곱하고, 문자 `c`를 **int**로 변환해서 더해준다.  
  숫자가 아닌 경우에는 `word` 문자열에 문자 `c`를 더해준다.  
- **word가 완성된 경우**  
  `word`가 `number` 리스트에 있는 경우에는  
  기존 `answer` 값에 **10**을 곱하고, `number` 리스트에서 `word` 값에 해당하는 인덱스 값을 더해준다.  

## 📝 Review

1번 문제다운 간단한 문자열 문제였다.
