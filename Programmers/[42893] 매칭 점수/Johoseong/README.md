# [42893] 매칭 점수
## Algorithm
- 정규 표현식
## Logic
- 정규표현식을 치밀하게 잘 쓰기만 하면 외부 링크 수와 매칭 문자들을 찾아서 문제를 쉽게 해결할 수 있다..
1. ```pages```에서 현재 url, 외부 링크된 url, word와 매칭하는 단어 개수를 세서 딕셔너리에 저장
- 유의점1 : 현재 url 뽑아내는 정규표현식에서 property 내용 안붙이니까 안됨..
- 유의점2 : word와 매칭하는 단어 수 셀 때, 앞 뒤가 문자 제외 기호면 구분되는 것으로 간주 (띄어쓰기 포함 숫자, 기호 등등)
```python
for i, page in enumerate(pages):
    page = page.replace('\n', ' ').lower()
    my = re.findall('<meta property="og:url" content="(\S+)"', page) # 현재 페이지 url
    links = re.findall('<a href="(https://[\S]*)"', page) # 외부 링크 url들
    basic_num = 0
    for f in re.findall(r'[a-zA-Z]+', page): # 기본 점수
        if f == word:
            basic_num += 1
    infos[my[0]] = [i, len(links), basic_num, 0, links] # [인덱스번호, 외부 링크 수, 기본점수, 매칭점수, 링크 url 정보]
```
2. 딕셔너리를 탐색하면서, 링크된 url들의 링크 점수를 올려줌
```python
for i in infos: # 링크 점수 구함
    for link in infos[i][4]:
        if link not in infos:
            continue
        infos[link][3] += (infos[i][2] / infos[i][1])
```
3. 매칭점수 구해서 매칭점수가 가장 큰 url의 인덱스를 찾음
```python
for i in infos: # 매칭 점수 구해서 가장 큰 인덱스 찾음
    matching = infos[i][2] + infos[i][3]
    if matching > answer:
        answer = matching
        index = infos[i][0]
```

## Review
미친 것 같다.. 문제는 자체는 쉬운데 정규표현식 예외가 너무 많아서 예외케이스 찾느라 죽는 줄 알았다.. 결국 딴 코드 참고함. 다시는 만나고 싶지 않은 문제 유형;;