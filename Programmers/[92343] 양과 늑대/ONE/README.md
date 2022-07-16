# [92343] 양과 늑대
## Algorithm
- **DFS**

## Logic
- 트리를 구성하여 전체를 완전탐색으로 DFS를 사용한다
- info의정보로 양과 늑대의 수를 세고 늑대 수가 양의 수 이상이면 return 한다
- 그리고 양의 수를 비교하여 최댓값을 찾고
- 파라미터로 넘어온 다음으로 갈 노드들의 정보를 임시 리스트에 추가한다
- 현재 노드의 자식들을 리스트에 추가하고 임시 리스트에서 현재 노드의 번호를 제거해준다
- 그리고 다음 노드로 옮겨가며 DFS 한다

```java
private void traversal(int num, int sheep, int wolf, List<Integer> next) {
    sheep += info[num] ^ 1;
    wolf += info[num];

    if (sheep <= wolf)
        return;

    answer = Math.max(answer, sheep);

    List<Integer> temp = new ArrayList<>(next);

    if (!tree[num].isEmpty())
        temp.addAll(tree[num]);
    temp.remove(Integer.valueOf(num));

    for (int n : temp)
        traversal(n, sheep, wolf, temp);
}
```

## Review
아이디어 자체는 어렵지 않았던 거같은데 구현이 오래 걸렸던 것 같다  
그리고 다른 사람의 코드를 보다가 ^ 연산을 이용하는 것이 신기해서 참고하여 바꿨다