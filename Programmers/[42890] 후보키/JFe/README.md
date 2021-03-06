# [42890] ํ๋ณดํค - Python

## ๐ Algorithm
**์กฐํฉ**

## ๐ป Logic

```Python
def check_uniqueness(relation, comb, answer_idx):
    tmp_set = set()
    for i in range(len(relation)):
        tmp = ''
        for c in comb: tmp += relation[i][c]
        tmp_set.add(tmp)
    if len(tmp_set) == len(relation): return True
    return False
```
- **์ ์ผ์ฑ ์ฒดํฌ ํจ์**  
    - ์กฐํฉ์ ํด๋นํ๋ ๋ฌธ์์ด๋ค์ ๋ค ๋ํด์ `tmp_set`์ ์ถ๊ฐํ๋ค. (`tmp_set`๋ **set**์ผ๋ก **์ค๋ณต ์ ๊ฑฐ**๋จ)  
    - ๊ธฐ์กด `relation`๊ณผ ํฌ๊ธฐ๋ฅผ ๋น๊ตํด์ ์ค๋ณต ์ ๊ฑฐ๋ ๋ถ๋ถ์ด ์์ผ๋ฉด **True**๋ฅผ ๋ฐํ  

```Python
def check_minimality(answer_idx, comb):
    for i in answer_idx:
        if i.issubset(set(comb)): 
            return False
    return True
```
- **์ต์์ฑ ์ฒดํฌ ํจ์**  
    - `answer_idx`์ ์๋ ๊ฐ๊ณผ **subset**์ ๋น๊ต  
    - ๋ถ๋ถ์งํฉ์ ํด๋น๋๋ฉด **False** ๋ฐํ  

```Python
# ์กฐํฉ ์์ฑ ํ, ํ๋ณดํค ํ์ธ
    for i in range(len(relation[0])):
        comb_list = combinations([int(x) for x in range(len(relation[0]))], i+1)
        for comb in comb_list:
            comb = list(comb)
            # ์ต์์ฑ ์ฒดํฌ
            if not check_minimality(answer_idx, comb): continue
            # ์ ์ผ์ฑ ์ฒดํฌ
            if check_uniqueness(relation, comb, answer_idx):
                answer += 1
                answer_idx.append(set(comb))
```
- **์กฐํฉ ์์ฑ ํ, ํ๋ณดํค ํ์ธ**  
    **์ต์์ฑ**์ ๋ง์กฑํ์ง ๋ชปํ๋ฉด, **continue**  
    **์ ์ผ์ฑ**์ ๋ง์กฑํ๋ฉด, `answer` **+1** ํด์ฃผ๊ณ , `answer_idx`์ ํด๋น ์กฐํฉ ์ถ๊ฐ  


## ๐ Review

์กฐํฉ์ ์ด์ฉํด์ ํ๋ฉด ๋๊ฒ ๋ค๊ณ  ๋ฐ๋ก ๋ ์ฌ๋ผ์ ์กฐํฉ์ ๋ง๋ค๊ณ  ํ๋ณดํค๊ฐ ๋  ์ ์๋์ง ํ์ธํ๋ ๋ฐฉ์์ผ๋ก ๊ตฌํํ๋ค.  
์ ์ผ์ฑ ์ฒดํฌํ๋ ๋ถ๋ถ์ ๋ฌธ์์ด์ด๋ผ๋ ํน์ฑ์ ์ด๋ ค ์ฝ๊ฒ ํ  ์ ์์๊ณ , ์ต์์ฑ ์ฒดํฌ๋ฅผ ์๋ชป ์ดํดํ์๋๋ฐ `issubset`์ ํตํด ๋ถ๋ถ์งํฉ์ ํ์ธํ๋ ๋ฐฉ์์ผ๋ก ๋ฐ๊ฟ์ ํด๊ฒฐํ๋ค.  
