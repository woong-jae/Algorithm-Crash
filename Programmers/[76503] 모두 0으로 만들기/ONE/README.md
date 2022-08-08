# [76503] 모두 0으로 만들기
## Algorithm
- **DFS**

## Logic
- 합을 계산하며 int 배열의 크기로는 값을 담을 수 없기 때문에 long 배열을 하나 만들어서 a 배열의 값을 옮겨 준다
- 모두 더한 값이 0이 되지 않으면 유효하지 않은 배열이기 때문에 -1 리턴
- List<>[] tree를 만들어서 간선의 정보를 저장하고 방문을 확인하기 위해 visites 배열 생성
- DFS로 방문하며 자식 노드들의 값을 부모에게 더해주며 answer에는 현재 노드 값의 절대값을 더해 카운트 해준다

```java
public long traversal(int index) {
    visited[index] = true;

    // 간선 노드를 확인하며 가중치 업데이트
    for (int i = 0; i < tree[index].size(); i++) {
        int curr = tree[index].get(i);
        if (!visited[curr]) {
            longA[index] += traversal(curr);
        }
    }

    answer += Math.abs(longA[index]);

    return longA[index];
}
```

## Review
처음에는 루트 노드가 뭔지 몰라서 헷갈렸고 int 배열을 그대로 사용해서 런타임 에러가 발생했는데  
금방 해결을 할수 있었고 합의 법칙에 의해 어느 노드에서 시작하던 같은 결과를 얻을 수 있다는 것을 알게 되었다  
쬐끔 까다로운 문제
