from collections import defaultdict
class Node:
    def __init__(self):
        self.count = 0
        self.flag = False
        self.child = defaultdict(Node)

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
    
def solution(words, queries):
    answer = []
    prefix = defaultdict(Trie)  # ?가 접두사로 올 때
    suffix = defaultdict(Trie)  # ?가 접미사로 올 때
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
    return answer