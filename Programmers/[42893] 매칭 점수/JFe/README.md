# [42893] ๋งค์นญ ์ ์ - Python

## ๐ Algorithm
**๋ฌธ์์ด(์ ๊ท ํํ์)**

## ๐ป Logic

```Python
    basic_score = defaultdict(int)      # ๊ธฐ๋ณธ ์ ์
    external_num = defaultdict(int)     # ์ธ๋ถ ๋งํฌ ์
    matching_score = defaultdict(int)   # ๋งค์นญ ์ ์ [๋งค์นญ ์ ์, index] ํํ
    link = defaultdict(list)            # ํด๋น ํ์ด์ง์ ๋งํฌ ๊ฑธ๋ฆฐ ํ์ด์ง ๋ชฉ๋ก
    page_idx = defaultdict(int)         # ํ์ด์ง๋ณ ์ธ๋ฑ์ค ์ ์ฅ
```
- **ํ์ํ dictionary ์์ฑ**  

```Python
    for i, page in enumerate(pages):
        # url ์ฐพ๊ธฐ
        url = re.search('(<meta property="og:url" content=")(https://\S*)"/>', page).group(2)
        page_idx[url] = i
        # ์ธ๋ถ ๋งํฌ ์ฐพ๊ธฐ
        external = re.findall('(<a href=")(https://\S*)">', page)
        for l in external:
            external_num[url] += 1
            link[l[1]].append(url)
        # ํ์ด์ง์์ ๋จ์ด ์ฐพ๊ธฐ
        all_word = re.findall('[a-z]+', page.lower())
        for w in all_word:
            # ๋จ์ด๊ฐ ๊ฐ์ผ๋ฉด ๊ธฐ๋ณธ ์ ์ +
            if w == word.lower():
                basic_score[url] += 1
```
- **url ์ฐพ๊ธฐ**  
    - ์ ๊ท ํํ์์ ์ด์ฉํด์ `'(<meta property="og:url" content=")(https://\S*)"/>'`์ ํด๋นํ๋ ๋ถ๋ถ **search** (`\S`๋ ๊ณต๋ฐฑ์๋ ๋ฌธ์)  
    - **group**์ ์ด์ฉํด์ `url` ๋ถ๋ถ ๋ฐ๋ก ์ ์ฅ  
    - ํด๋น `url`์ ์ธ๋ฑ์ค `page_idx`์ ์ ์ฅ  
- **์ธ๋ถ ๋งํฌ ์ฐพ๊ธฐ**  
    - ์ ๊ท ํํ์์ ์ด์ฉํด์ `'(<a href=")(https://\S*)">'`์ ํด๋นํ๋ ๋ถ๋ถ ์ ๋ถ **findall** (`\S`๋ ๊ณต๋ฐฑ์๋ ๋ฌธ์)  
    - ํด๋น `url`์ `external_num` ๋๋ ค์ฃผ๊ณ , ํด๋น ๋งํฌ์ `link` ๋์๋๋ฆฌ์ `url` ์ถ๊ฐ  
- **ํ์ด์ง์์ ๋จ์ด ์ฐพ๊ธฐ**  
    - `page`๋ฅผ ์ ๋ถ **์๋ฌธ์**๋ก ๋ฐ๊พธ๊ณ , `'[a-z]+'`๋ฅผ ์ด์ฉํด์ ์๋ฌธ์๋ก ๊ตฌ์ฑ๋ ๋จ์ด ์ ๋ถ **findall**  
    - ํด๋น ๋จ์ด๋ค๊ณผ **์๋ฌธ์**๋ก ๋ฐ๊พผ `word`๊ฐ ๊ฐ์ผ๋ฉด ํด๋น `url`์ `basic_score`๋ฅผ ์ฆ๊ฐ  

```Python
# ๋งค์นญ ์ ์ ๊ณ์ฐ
    for key, value in page_idx.items():
        temp = 0
        # ๋งํฌ ์ ์ ๊ณ์ฐ
        for v in link[key]:
            temp += basic_score[v] / external_num[v]
        matching_score[key] = [temp + basic_score[key], value]  # ๋งค์นญ ์ ์ [๋งค์นญ ์ ์, index] ํํ
    # ๋งค์นญ ์ ์ ๋ด๋ฆผ์ฐจ์, index ์ค๋ฆ์ฐจ์ ์ ๋ ฌ
    sorted_list = sorted(matching_score.values(), key=lambda x:(-x[0],x[1]))
```
- **๋งค์นญ ์ ์ ๊ณ์ฐ**  
    - ๊ฐ `url`๋ง๋ค ์ฐ๊ฒฐ๋ **๋งํฌ ์ ์**๋ฅผ ๊ณ์ฐํ๊ณ , **๊ธฐ๋ณธ ์ ์**์ ๋ํด์ `matching_score`์ ์ ์ฅํด์ค๋ค. (์ ์ฅ ํํ๋ `[๋งค์นญ ์ ์, index]`)  
    - **๋งค์นจ ์ ์**์ ๋ํด์ **๋ด๋ฆผ์ฐจ์**, ๊ทธ ๋ค์ `index`์ ๋ํด **์ค๋ฆ์ฐจ์** ์ ๋ ฌํด์ค๋ค.  



## ๐ Review

์ ๊ท ํํ์์ ์ฌ์ฉํ๊ณ  ์ ๋ ฌ๋ง ํ๋ฉด ๋๋ ๋ฌธ์ ์ง๋ง ๋ฌธ์  ์์ฒด๊ฐ ๋๋ฌด ๊ธธ๊ณ  ๋ณต์กํ๋ค๊ณ  ๋๊ปด์ก๋ค..  
๊ทธ๋๋ ์ ๊ท ํํ์ ์ฌ์ฉํ๋ ๋ฐฉ๋ฒ์ ๋ ์ ๋๋ก ์ตํ ์ ์์ด์ ์ข์๋ ๋ฌธ์ !
