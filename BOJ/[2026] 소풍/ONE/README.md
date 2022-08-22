# [2026] 소풍
## Algorithm
- **Backtracking**

## Logic
- 입력받은 정보로 친구들간의 인접 트리 생성
- 1번 친구부터 친구수가 K-1 이상일 경우에 경우의 수를 탐색한다
  - N 명의 친구중 K-1 명을 뽑는디
  - 뽑은 친구들의 트리에 들어가서 본인과 처음에 시작한 친구를 제외하고 경우의 수에 있는 친구리스트를 검색해서
  - 만약에 경우의 수의 친구들을 서로 모두 가지고 있다면 정답리스트에 추가
- 정답을 오름차순 정렬하여 출력하거나 모든 경우를 탐색해도 정답리스트가 비었다면 -1 출력

```java
private static void dfs(int i, int depth, int n, boolean[] check, List<Integer> list) {
    if (!answer.isEmpty())
        return;

    if (depth == n) {
        for (int index : list)
            for (int num : list)
                if (num != index && !tree[index].contains(num))
                    return;
        answer.add(i);
        answer.addAll(list);
        return;
    }

    for (int j = 0; j < check.length; j++)
        if (!check[j]) {
            check[j] = true;
            list.add(tree[i].get(j));
            dfs(i, depth + 1, n, check, list);
            list.remove(tree[i].get(j));
        }

}
```

## Review
처음에는 뭔가 되게 어렵게 생각해서 오래걸렸는데 이게 될까해서 했는데 됐다  
근데 뭔가 좀 더 효율적으로 바꿀수 있을거같아서 나중에 한번 더 봐야겠다!
