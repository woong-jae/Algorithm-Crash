def insert(trie, w, l):
    cur_node = trie
    for a in w:
        cur_node.setdefault(a, [{}, {}])
        if l not in cur_node[a][0]: # 자식 단어 길이 저장!!
            cur_node[a][0][l] = 0
        cur_node[a][0][l] += 1
        cur_node = cur_node[a][1]

def search(trie, q, l):
    cur_node = trie
    cnt = 0
    for a in q:
        if a == '?': # 물음표면? 지금까지 매칭된 단어들 길이랑 쿼리 길이 같은거 count
            for i in cur_node:
                if l in cur_node[i][0]:
                    cnt += cur_node[i][0][l]
            return cnt

        if a in cur_node: # 해당 알파벳이 트리에 있으면 다음노드로
            cur_node = cur_node[a][1]
        else:
            break
    return cnt

def solution(words, queries):
    answer = []
    trie = {}
    reverse_trie = {}

    for w in words:
        insert(trie, w, len(w)) # 일반적인 경우
        insert(reverse_trie, reversed(w), len(w)) # 단어 뒤집은 경우

    for q in queries:
        if q[0] == '?' and q[-1] == '?': # 다 물음표
            answer.append(search(trie, q, len(q)))
        elif q[0] == '?': # 앞에가 물음표 -> 뒤집어서 단어검색!!!!!!
            answer.append(search(reverse_trie, reversed(q), len(q)))
        else: # 뒤에가 물음표
            answer.append(search(trie, q, len(q)))
    return answer