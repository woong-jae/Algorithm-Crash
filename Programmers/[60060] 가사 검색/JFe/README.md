# [60060] 가사 검색 - Python

## 🔍 Algorithm
**Trie**

## 💻 Logic

```Python
class Node:
    def __init__(self):
        self.count = 0
        self.flag = False
        self.child = defaultdict(Node)
```
- **Trie에 사용할 Node 클래스**  
    - count : child 수 확인하기 위해  
    - flag : 단어가 완성되는지 확인하는 용도  
    - child : defaultdict 사용  

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
- **Trie 클래스**  
    - insert() : 해당 문자를 찾고, child를 따라가면서 추가  
    - search() : 해당 문자를 끝까지 따라간 뒤, count 반환  


```Python
    for word in words:
        # 단어 길이에 맞게 Trie 생성
        if len(word) not in prefix:
            prefix[len(word)] = Trie()
            suffix[len(word)] = Trie()
        prefix[len(word)].insert(word)
        suffix[len(word)].insert(word[::-1])
        
    for q in queries:
        cnt = q.count('?')
        # ?가 접두사로 올 때
        if q[0] == '?':
            answer.append(suffix[len(q)].search(q[cnt:][::-1]))
        # ?가 접미사로 올 때
        else:
            answer.append(prefix[len(q)].search(q[:len(q)-cnt]))
```
- 단어 길이가 맞는지부터 확인해야 하기 때문에 단어 길이에 맞게 Trie 생성  
- ?가 접두사로 오는 경우와 접미사로 오는 경우 나눠서 판단  


## 📝 Review

저번에 Trie로 구현할 수 있는 문제를 Trie 사용하지 않고 다른 방식으로 풀었어서 이번에는 Trie를 구현하는데 집중해서 풀었다.  
아직 알고 문제 풀면서 파이썬에서 클래스 사용하는게 익숙하지 않지만 익숙해지도록 많이 풀어봐야겠다..
