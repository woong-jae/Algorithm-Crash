# [17686] 파일명 정렬 - Python

## 🔍 Algorithm
**문자열, 정렬**

## 💻 Logic

```Python
    for i, file in enumerate(files):
        # head 매치
        pattern = re.compile('[^0-9]+')
        m = pattern.match(file)
        head = m.group().lower()
        # number 매치
        pattern = re.compile('[0-9]+')
        m = pattern.match(file[m.end():])
        number = int(m.group())
        # file_list에 저장
        file_list.append([i, head, number, file])
```
- **head 매치**  
    정규 표현식 `'[^0-9]+'`을 이용해서 숫자를 제외한 문자를 숫자 나올 때까지 찾고, `lower()`를 통해 대소문자 통일해준다.  
- **number 매치**  
    정규 표현식 `'[0-9]+'`을 이용해서 숫자 아닌 값 나올 때까지 찾고, 정수형으로 변경해서 저장한다.  

```Python
# head, number, index 순으로 정렬
    file_list = sorted(file_list, key = lambda x: (x[1], x[2], x[0]))
```
- **head, number, index 순으로 정렬**  
    lambda 식을 통해 head, number, index 순으로 정렬  


## 📝 Review

요즘 정규표현식을 사용하는 문제를 많이 풀어봐서 어렵지 않았던 문제  
정렬하는 과정도 lambda 식을 이용해서 빠르게 구현할 수 있었다.  
