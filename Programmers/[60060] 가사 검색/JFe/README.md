# [60060] ๊ฐ์ฌ ๊ฒ์ - Python

## ๐ Algorithm
**Trie**

## ๐ป Logic

```Python
class Node:
    def __init__(self):
        self.count = 0
        self.flag = False
        self.child = defaultdict(Node)
```
- **Trie์ ์ฌ์ฉํ  Node ํด๋์ค**  
    - count : child ์ ํ์ธํ๊ธฐ ์ํด  
    - flag : ๋จ์ด๊ฐ ์์ฑ๋๋์ง ํ์ธํ๋ ์ฉ๋  
    - child : defaultdict ์ฌ์ฉ  

```Python
class Trie:
    def __init__(self):
        self.root = Node()
        
    def insert(self, word):
        node = self.root
        node.count += 1
        for w in word:
            node = node.child[w]
            node.count += 1
        node.flag = True
    
    def search(self, word):
        node = self.root
        for w in word:
            if w not in node.child:
                return 0
            node = node.child[w]
        return node.count
```
- **Trie ํด๋์ค**  
    - insert() : ํด๋น ๋ฌธ์๋ฅผ ์ฐพ๊ณ , child๋ฅผ ๋ฐ๋ผ๊ฐ๋ฉด์ ์ถ๊ฐ  
    - search() : ํด๋น ๋ฌธ์๋ฅผ ๋๊น์ง ๋ฐ๋ผ๊ฐ ๋ค, count ๋ฐํ  


```Python
    for word in words:
        # ๋จ์ด ๊ธธ์ด์ ๋ง๊ฒ Trie ์์ฑ
        if len(word) not in prefix:
            prefix[len(word)] = Trie()
            suffix[len(word)] = Trie()
        prefix[len(word)].insert(word)
        suffix[len(word)].insert(word[::-1])
        
    for q in queries:
        cnt = q.count('?')
        # ?๊ฐ ์ ๋์ฌ๋ก ์ฌ ๋
        if q[0] == '?':
            answer.append(suffix[len(q)].search(q[cnt:][::-1]))
        # ?๊ฐ ์ ๋ฏธ์ฌ๋ก ์ฌ ๋
        else:
            answer.append(prefix[len(q)].search(q[:len(q)-cnt]))
```
- ๋จ์ด ๊ธธ์ด๊ฐ ๋ง๋์ง๋ถํฐ ํ์ธํด์ผ ํ๊ธฐ ๋๋ฌธ์ ๋จ์ด ๊ธธ์ด์ ๋ง๊ฒ Trie ์์ฑ  
- ?๊ฐ ์ ๋์ฌ๋ก ์ค๋ ๊ฒฝ์ฐ์ ์ ๋ฏธ์ฌ๋ก ์ค๋ ๊ฒฝ์ฐ ๋๋ ์ ํ๋จ  


## ๐ Review

์ ๋ฒ์ Trie๋ก ๊ตฌํํ  ์ ์๋ ๋ฌธ์ ๋ฅผ Trie ์ฌ์ฉํ์ง ์๊ณ  ๋ค๋ฅธ ๋ฐฉ์์ผ๋ก ํ์์ด์ ์ด๋ฒ์๋ Trie๋ฅผ ๊ตฌํํ๋๋ฐ ์ง์คํด์ ํ์๋ค.  
์์ง ์๊ณ  ๋ฌธ์  ํ๋ฉด์ ํ์ด์ฌ์์ ํด๋์ค ์ฌ์ฉํ๋๊ฒ ์ต์ํ์ง ์์ง๋ง ์ต์ํด์ง๋๋ก ๋ง์ด ํ์ด๋ด์ผ๊ฒ ๋ค..
