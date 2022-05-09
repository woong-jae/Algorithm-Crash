# [81305] 시험장 나누기
## Algorithm
- Binary Search
- DFS
## Logic
가장 간단한 방법은 간선 `k-1`개를 끊는 모든 경우의 수에 대해 조사하는 방법이다.

하지만, 이 방법은 간선이 너무 많기 떄문에 효율성 테스트를 통과하지 못한다.

이때 최적화를 위해 필요한 개념이 **'파라메트릭 서치'**다.
파라메트릭 서치는 최적화 문제를 결정 문제로 바꾸어 풀어주는 방식이다.

이제 문제는 '인원이 가장 많은 그룹의 인원이 최소화되도록 k개의 그룹으로 나누었을 때, 최소화된 최대 그룹의 인원'을 찾는 문제가 아닌,
'최대 그룹의 인원 L이 주어졌을 때, k개의 그룹으로 나눌 수 있는가?'로 바꿀 수 있다.

그러면 우리는 (전체 가중치 합)/k <= L <= (전체 가중치 합)을 만족하는 L 중, k개의 그룹으로 나눌 수 있는 L의 최소값을 찾으면 된다.

```js
let right = num.reduce((prev, cur) => prev + cur, 0), left = floor(right / k);
while(left <= right) {
    const mid = floor((left + right) / 2);
    if(getGroup(mid) <= k) right = mid - 1;
    else left = mid + 1;
}
```

이제 해당하는 L로 트리를 나눴을 때 몇 개의 그룹이 나오는지 찾으면 된다.

그룹을 찾기 위해 루트부터 DFS로 트리를 들어가면서 탐색한다.
탐색하면서 전체 그룹 수(`groups`)와 각 노드가 속한 그룹의 가중치(`cache`)를 저장한다.

> 재귀로 DFS를 실행할 경우 스택 오버플로우 때문에 런타임 에러가 발생한다.

현재 노드에서 그룹을 나눌 수 있는 경우는 4가지가 존재한다.
1. 현재 노드와 왼쪽, 오른쪽의 가중치를 합쳐도 L보다 작은 경우 한 그룹으로 묶는다.
2. 왼쪽 혹은 오른쪽만 합칠 경우.
3. 왼쪽과 오른쪽을 잘라낼 경우.
4. 현재 노드가 L보다 큰경우는 이미 성립이 안하기 때문에 그룹을 무한대로 둔다.

```js
function getGroup(limit) {
    const cache = Array(num.length).fill(0);
    const visited = Array(num.length).fill(false);
    let groups = 1;
    
    let stack = [root];
    while(stack.length) {
        const cur = stack.pop();
        const [left, right] = links[cur];
        if(left >= 0 && !visited[left]) {
            stack.push(cur, left);
            continue;
        }
        if(right >= 0 && !visited[right]) {
            stack.push(cur, right);
            continue;
        }
        
        let leftValue = left >= 0 ? cache[left] : 0;
        let rightValue = right >= 0 ? cache[right] : 0;
        
        if(num[cur] + leftValue + rightValue <= limit) {
            cache[cur] = num[cur] + leftValue + rightValue;
        }
        else if(num[cur] + leftValue <= limit || num[cur] + rightValue <= limit) {
            groups++;
            cache[cur] = num[cur] + Math.min(leftValue, rightValue);
        }
        else if(num[cur] <= limit){
            groups += 2;
            cache[cur] = num[cur];
        }
        else {
            groups = Infinity;
        }
        
        visited[cur] = true;
    }
    return groups;
}
```

## Review
Bruteforce로 푸는 방법은 바로 생각났지만, 효율성은 도저히 엄두가 안나서 바로 해설을 봤다.
해설을 해석하고 구현하는데도 한참 걸렸다. 이걸 시간안에 푸는 미친 사람이 있을까...

파라메트릭 서치 자체는 몇 번 접해봐서 쉽게 이해했는데, 트리를 나누는 부분이 어려웠다.
아직 트리와 덜 친해서 이쪽으로 머리가 아예 안돌아가는 듯하다. 이 정도 문제는 아니더라도 다른 트리문제를 많이 풀어봐야겠다.