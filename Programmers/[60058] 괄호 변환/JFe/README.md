# [60058] ๊ดํธ ๋ณํ - Python

## ๐ Algorithm
**๋ฌธ์์ด**

## ๐ป Logic

```Python
# ์ ์ฒด ์ฌ๊ท ํจ์
    def recursion(s):
        if len(s) == 0: return ''   # 1. ๋น ๋ฌธ์์ด์ธ ๊ฒฝ์ฐ
        index = separate(s)
        u, v = s[:index+1], s[index+1:]
        # 3. u๊ฐ ์ฌ๋ฐ๋ฅธ ๊ดํธ ๋ฌธ์์ด์ธ ๊ฒฝ์ฐ
        if check_correct(u):
            return u + recursion(v)
        # 4. u๊ฐ ์ฌ๋ฐ๋ฅธ ๊ดํธ ๋ฌธ์์ด์ด ์๋ ๊ฒฝ์ฐ
        else:
            temp = reverse(u)
            return '(' + recursion(v) + ')' + temp
```
- **์ฌ๊ท ํจ์**  
    - ๋น ๋ฌธ์์ด์ธ ๊ฒฝ์ฐ, ๋น ๋ฌธ์์ด ๋ฐํ  
    - `u`๊ฐ ์ฌ๋ฐ๋ฅธ ๊ดํธ ๋ฌธ์์ด์ธ ๊ฒฝ์ฐ, ์๋ ๊ฒฝ์ฐ ๋๋ ์ ์ฒ๋ฆฌ  

```Python
# ์ฌ๋ฐ๋ฅธ ๊ดํธ ๋ฌธ์์ด์ธ์ง ํ์ธ
    def check_correct(s):
        left, right = 0, 0
        for i, c in enumerate(s):
            if c == '(':
                left += 1
            else:
                right += 1
            if left < right:
                return False
        return True
```
- **์ฌ๋ฐ๋ฅธ ๊ดํธ ๋ฌธ์์ด์ธ์ง ํ์ธํ๋ ํจ์**  
    - `left`๋ณด๋ค `right`๊ฐ ์ปค์ง๋ฉด **False** ๋ฐํ  

```Python
# 2. ๋ฌธ์์ด u, v๋ก ๋ถ๋ฆฌ (๋ถ๋ฆฌํ๋ ๊ธฐ์ค ์ธ๋ฑ์ค ๋ฐํ)
    def separate(s):
        left, right = 0, 0
        for i, c in enumerate(s):
            if c == '(':
                left += 1
            else:
                right += 1
            if left > 0 and right > 0 and left == right:
                return i
```
- **๋ฌธ์์ด u, v๋ก ๋ถ๋ฆฌํ๋ ํจ์**  
    - `left`, `right`๊ฐ **1 ์ด์**์ด๋ฉด์ **๊ฐ์ ๋**, ๊ทธ ์ธ๋ฑ์ค ๋ฐํ  


```Python
# 4-4. u ๋ฌธ์์ด ๋ณํ
    def reverse(s):
        temp = s[1:-1]
        temp = temp.replace('(', '-').replace(')', '(').replace('-', ')')
        return temp
```
- **u ๋ฌธ์์ด ๋ณํํ๋ ํจ์**  
    - ์, ๋ค ๋ฌธ์ **slicing** ํ๊ณ ,  
    - `'('`, `')'` ๋ฌธ์ **replace**  


## ๐ Review

๋ฌธ์ ์ ์ ํ์๋ ๊ทธ๋๋ก ๊ตฌํํ๋ฉด ๋๋ ๋ฌธ์   
๋ฌธ์์ด ๋ณํํ๋ ๋ถ๋ถ์์ replace๋ฅผ 3๋ฒ ์จ์ swap ํ๋๋ฐ, ์ด ๋ถ๋ถ์ ๋ ๊น๋ํ๊ฒ ์งค ์ ์์๊น  
