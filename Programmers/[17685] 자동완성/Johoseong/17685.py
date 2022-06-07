def insert(trie, s):
    cur_node = trie
    for c in s: # 받은 문자열 하나하나에 대해서
        cur_node.setdefault(c, [0, {}]) # 공통되는 문자 count할 값도 있어야함!!!!
        cur_node[c][0] += 1
        cur_node = cur_node[c][1]
 
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

def solution(words):
    answer = 0
    trie = {} # Trie 트리

    for word in words:
        insert(trie, word) # 트리생성
    for word in words:
        answer += search(trie, word) # 공통된 문자 카운트

    return answer