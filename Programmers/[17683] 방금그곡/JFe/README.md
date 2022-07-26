# [17683] 방금그곡 - Python

## 🔍 Algorithm
**문자열**

## 💻 Logic

```Python
# '#' 들어간 음 변환
    for org, rep in zip(['A#', 'C#', 'D#', 'F#', 'G#'], ['H', 'I', 'J', 'K', 'L']):
        m = m.replace(org, rep)
```
- **'#' 들어간 음 변환**  
    탐색 편리하게 하기 위해서 '#' 들어간 문자들 전부 다른 문자들로 변환  

```Python
    # time만큼 악보 정보 반복해서 message 저장
        for i in range(time):
            message += info[3][i%len(info[3])]
    # pattern이 있고, 현재 저장된 길이보다 크면 answer 변경
        if pattern.search(message) and length < time:
            answer = info[2]
            length = time
```
- **time만큼 악보 정보 반복해서 message 저장**  
- **pattern이 있고, 현재 저장된 길이보다 크면 answer 변경**  


## 📝 Review

정규표현식 써서 해당하는 문자열이 있는지 찾으면 되는 문제.  
'#'이 들어간 부분을 계산하기 쉽게 다른 문자들로 변환해두고 푸는 것이 포인트
