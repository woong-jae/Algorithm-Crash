# [17685] 자동완성
## Algorithm
- Trie
## Logic
- 주어진 단어들로 Trie 트리를 만들고 탐색해서 해결해야함. 중요한 점은, 노드(문자)를 공유하는 단어 등장 시 해당 노드 등장 횟수를 기억해둬야함! 그래야지 공유하는 부분이 어디까지인지 알 수 있음.
1. 주어진 단어들로 Trie 트리를 생성
- Trie 노드의 0번쨰 인덱스 : 지금 노드를 공유하는 문자 등장 시 count
- 1번째 인덱스 : 자식 노드
```python
def insert(trie, s):
    cur_node = trie
    for c in s: # 받은 문자열 하나하나에 대해서
        cur_node.setdefault(c, [0, {}]) # 공통되는 문자 count할 값도 있어야함!!!!
        cur_node[c][0] += 1
        cur_node = cur_node[c][1]
```
2. 주어진 단어들로 트리 탐색하면서 공통 문자 개수 count
- 만약 노드의 0번째 인덱스가 1이다 -> 그 노드 밑에는 특정 한 단어만 존재한다는 뜻이므로 더 이상 탐색할 필요없음
```python
def search(trie, s):
    cur_node = trie
    cnt = 0
    for c in s:
        cnt += 1 # 공통부분 count += 1
        if cur_node[c][0] == 1: # count가 1이면 -> 그 노드부터는 공통부분 없는 단어라서 탐색 필요 X
            break
        if c in cur_node:
            cur_node = cur_node[c][1]
    return cnt
```
3. 단어들마다 공통(공유하는) 문자 개수 다 count한게 answer

## Review
처음에 KMP를 응용해야하나 싶었는데 아니었고 결국 다른 코드 참고했다. Trie 자료구조를 처음 접해서 개념부터 공부해볼 수 있어서 좋았음. 문자열 자동완성/사전 찾기 같은 문제는 Trie 쓰기!