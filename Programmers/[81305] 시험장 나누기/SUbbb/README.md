# [81305] 시험장 나누기

## Algorithm
- Parametric Search
- DFS
- 이분 탐색

## Logic

```java
private int dfs(int cur, int limit) {
    // 왼쪽과 오른쪽 자식 트리에서 넘어오는 인원 수
    int leftValue = 0, rightValue = 0;
    if (left[cur] != -1) leftValue = dfs(left[cur], limit);
    if (right[cur] != -1) rightValue = dfs(right[cur], limit);
    
    // 두 자식 트리에서 넘어오는 인원을 모두 감당할 수 있는 경우, 그룹 수는 증가하지 않음
    if (x[cur] + leftValue + rightValue <= limit) 
        return x[cur] + leftValue + rightValue;
    
    // 두 자식 트리 중 작은 값을 합쳐야 감당할 수 있는 경우, 즉 자식 노드 하나를 끊는 경우로 그룹이 1개 추가
    if (x[cur] + Math.min(leftValue, rightValue) <= limit) {
        count++;
        return x[cur] + Math.min(leftValue, rightValue);
    }
    
    // 두 자식 트리 모두 감당할 수 없어서 둘 다 끊는 경우, 그룹이 2개 추가
    count += 2;
    return x[cur];
}
```

- 주어진 트리에서 각 그룹의 수를 x명으로 제한할 때 필요한 그룹의 수를 계산해야 한다.
- 각 그룹의 수를 x명으로 제한할 때 필요한 그룹의 수를 계산하기 위해 그리디를 사용하여 최대한 그룹 생성을 미루면서 위로 올려보낸다.
  - 부모 노드에서 자식 노드를 더 이상 감당할 수 없는 경우, 값이 더 작은 노드를 챙기고 다른 노드를 잘라낸다.
- 그룹으로 나눌 때 아래 3가지 경우가 존재한다.
  - 부모 노드가 자식 노드 모두 챙기기 : 그룹 + 0, 세 노드의 합을 더 부모 노드로 전달
  - 자식 노드 중 1개와 부모 노드를 챙기기 : 그룹 + 1, 자식 노드 중 최솟값 + 부모 노드의 값을 더 부모 노드로 전달
  - 자식 노드를 하나도 챙기지 않기 : 그룹 + 2, 부모 노드의 값만 더 부모 노드로 전달
- DFS를 통해 리프 노드부터 값을 위로 올려보내는 작업을 반복한다.

## Review
- 처음 보는 알고리즘을 사용하는 문제여서 참고를 찬찬히 읽어보면서 이해하려고 했다.
- 각 그룹의 수를 x명으로 제한할 떄 그룹의 수가 k개 이하인지를 판단하는 결정 문제 즉, **Parametric Search**를 사용하는 문제를 처음 접한 것 같다... 문제를 다양하게 풀어볼 수 있어야겠다.

## **참고**
[[프로그래머스] 시험장 나누기 / 2021 카카오 채용연계형 인턴십 - JAVA](https://blog.encrypted.gg/1003)