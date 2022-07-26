# [42893] 매칭 점수 - Python

## 🔍 Algorithm
**문자열(정규 표현식)**

## 💻 Logic

```Python
    basic_score = defaultdict(int)      # 기본 점수
    external_num = defaultdict(int)     # 외부 링크 수
    matching_score = defaultdict(int)   # 매칭 점수 [매칭 점수, index] 형태
    link = defaultdict(list)            # 해당 페이지에 링크 걸린 페이지 목록
    page_idx = defaultdict(int)         # 페이지별 인덱스 저장
```
- **필요한 dictionary 생성**  

```Python
    for i, page in enumerate(pages):
        # url 찾기
        url = re.search('(<meta property="og:url" content=")(https://\S*)"/>', page).group(2)
        page_idx[url] = i
        # 외부 링크 찾기
        external = re.findall('(<a href=")(https://\S*)">', page)
        for l in external:
            external_num[url] += 1
            link[l[1]].append(url)
        # 페이지에서 단어 찾기
        all_word = re.findall('[a-z]+', page.lower())
        for w in all_word:
            # 단어가 같으면 기본 점수 +
            if w == word.lower():
                basic_score[url] += 1
```
- **url 찾기**  
    - 정규 표현식을 이용해서 `'(<meta property="og:url" content=")(https://\S*)"/>'`에 해당하는 부분 **search** (`\S`는 공백없는 문자)  
    - **group**을 이용해서 `url` 부분 바로 저장  
    - 해당 `url`의 인덱스 `page_idx`에 저장  
- **외부 링크 찾기**  
    - 정규 표현식을 이용해서 `'(<a href=")(https://\S*)">'`에 해당하는 부분 전부 **findall** (`\S`는 공백없는 문자)  
    - 해당 `url`의 `external_num` 늘려주고, 해당 링크의 `link` 딕셔너리에 `url` 추가  
- **페이지에서 단어 찾기**  
    - `page`를 전부 **소문자**로 바꾸고, `'[a-z]+'`를 이용해서 영문자로 구성된 단어 전부 **findall**  
    - 해당 단어들과 **소문자**로 바꾼 `word`가 같으면 해당 `url`의 `basic_score`를 증가  

```Python
# 매칭 점수 계산
    for key, value in page_idx.items():
        temp = 0
        # 링크 점수 계산
        for v in link[key]:
            temp += basic_score[v] / external_num[v]
        matching_score[key] = [temp + basic_score[key], value]  # 매칭 점수 [매칭 점수, index] 형태
    # 매칭 점수 내림차순, index 오름차순 정렬
    sorted_list = sorted(matching_score.values(), key=lambda x:(-x[0],x[1]))
```
- **매칭 점수 계산**  
    - 각 `url`마다 연결된 **링크 점수**를 계산하고, **기본 점수**와 더해서 `matching_score`에 저장해준다. (저장 형태는 `[매칭 점수, index]`)  
    - **매침 점수**에 대해서 **내림차순**, 그 다음 `index`에 대해 **오름차순** 정렬해준다.  



## 📝 Review

정규 표현식을 사용하고 정렬만 하면 되는 문제지만 문제 자체가 너무 길고 복잡하다고 느껴졌다..  
그래도 정규 표현식 사용하는 방법을 더 제대로 익힐 수 있어서 좋았던 문제!
