# [92343] 양과 늑대

## Algorithm
- BFS

## Logic

```java
private ArrayList<Integer> makeList(ArrayList<Integer> nowNodeList, int nowN, int nextNode) {
    // 이동할 수 있는 노드를 저장할 리스트
    ArrayList<Integer> nextList = new ArrayList<>();

    // 다음 노드에서 갈 수 있는 노드를 저장, 연결된 자식 노드에 대해서만!
    for (int fromNextNode : infoList.get(nextNode)) nextList.add(fromNextNode);

    // 현 노드까지 오는데 방문했던 노드들 중 현 노드와 직전에 방문한 노드를 제외하고 저장, DFS로 따지면 백트래킹하는 느낌..
    for (int checkNode : nowNodeList) 
        if (checkNode != nowN && checkNode != nextNode)
            nextList.add(checkNode);
    
    return nextList;
}
```

- 현 노드에서 갈 수 있는 노드 리스트를 생성하는 함수
- 연결된 자식 노드로만 이동할 수 있는 게 아니라, 지나왔던 노드 또한 재방문 가능하기에 두 번째 반복문에서 이를 처리한다.

## Review
- 처음에는 DFS로 해결해야 하나 생각해보다가, 뭔가 BFS로 풀어보고 싶었고, 양의 수와 늑대의 수에 따라 원래는 못 갔던 노드라도 다시 갈 수 있는 경우가 발생하기에 일종의 백트래킹 구현이 필요했다.
  - 아이디어는 잡을 수 있었으나, 다시 방문해야 하는 경우에 대한 구현에 있어서는 참고가 필요했다...
  - 참고를 보고 나서, 이런 응용 문제를 시간 안에 풀어야 박살낼 수 있지 않을까 .. 하는 생각 ...