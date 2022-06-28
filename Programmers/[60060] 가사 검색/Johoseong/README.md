# [60060] 가사 검색
## Algorithm
- Trie

## Logic
- 핵심1 : 앞에가 물음표고 뒤에가 알파벳인 경우를 빠르기 찾기 위해서 **words를 뒤집어서 만든 reverse Trie트리**를 만듦
- 핵심2 : Trie트리의 각 노드에 value로 **자식 단어들 길이**를 저장 -> 나중에 서치할 때, 물음표 만나면 현재 노드에서 지금 쿼리랑 길이 같은 것만 count하면 됨
1. words로 Trie트리와 words를 뒤집어서 reverse Trie트리를 만듦
```python
def insert(trie, w, l):
    cur_node = trie
    for a in w:
        cur_node.setdefault(a, [{}, {}])
        if l not in cur_node[a][0]: # 자식 단어 길이 저장!!
            cur_node[a][0][l] = 0
        cur_node[a][0][l] += 1
        cur_node = cur_node[a][1]
...
for w in words:
    insert(trie, w, len(w)) # 일반적인 경우
    insert(reverse_trie, reversed(w), len(w)) # 단어 뒤집은 경우
```
2. 쿼리를 찾을 때 앞에가 물음표인 경우와 뒤에가 물음표인 경우를 나눠서 search
```python
for q in queries:
    if q[0] == '?' and q[-1] == '?': # 다 물음표
        answer.append(search(trie, q, len(q)))
    elif q[0] == '?': # 앞에가 물음표 -> 뒤집어서 단어검색!!!!!!
        answer.append(search(reverse_trie, reversed(q), len(q)))
    else: # 뒤에가 물음표
        answer.append(search(trie, q, len(q)))
```
3. search 시, 쿼리에서 물음표를 만나면 현재 노드의 value(자식 단어 길이 저장해둠)를 확인 -> 자식 단어 중 쿼리랑 길이 같은 것 count
```python
def search(trie, q, l):
    cur_node = trie
    cnt = 0
    for a in q:
        if a == '?': # 물음표면? 현재 노드의 자식 단어들 길이 == 쿼리 길이 같은거 count
            for i in cur_node:
                if l in cur_node[i][0]:
                    cnt += cur_node[i][0][l]
            return cnt

        if a in cur_node: # 해당 알파벳이 트리에 있으면 다음노드로
            cur_node = cur_node[a][1]
        else:
            break
    return cnt
```

## Review
6주차 자동완성이랑 느낌이 비슷해서 Trie로 풀었는데 맞아서 기분이 매우 좋다.. reverse Trie 만든게 핵심이었는듯! 처음에 자식 단어 길이를 배열로 줄줄 저장했다가 효율성 1개 시간초과나서 딕셔너리로 바꿨더니 바로 통과됐다. 딕셔너리의 중요성..