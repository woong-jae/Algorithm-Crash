# [72416] 매출 하락 최소화
## Algorithm
- DP
## Logic
한 그룹에서 선택할 수 있는 경우의 수는 다음과 같다.
- Root를 고른 경우: child를 안골랐을 때 하위 그룹의 최소 매출액을 모두 더한다.
- Root를 고르지 않은 경우: child 하나를 무조건 고르고, 나머지는 안골랐을 때 하위 그룹의 최소 매출액을 모두 더한다.

이것을 재귀적으로 들어가면 child를 안고르고 하위 그룹에 갔어도, 하위 그룹에서는 root를 고른 경우를 탐색하기 때문에 모든 경우를 탐색할 수 있게 된다.

중복되는 계산을 할 수 있기 때문에 이미 계산한 하위 그룹은 배열에 저장한다.
```js
const select = (current, picked) => {
    const children = adj_list.get(current);
    if(children.length === 0) return picked ? sales[current] : 0;
    if(cache[current][picked]) return cache[current][picked];
    
    // root를 고른 경우
    let min = children.reduce((prev, next) => prev + select(next, 0), sales[current]);
    // root를 안 고른 경우
    if(picked) return cache[current][picked] = min;
    
    children.forEach((next, index) => {
        let count = select(next, 1);
        for(let i = 0; i < children.length; i++) {
            if(i === index) continue; 
            count += select(children[i], 0);
        }
        min = Math.min(min, count);
    });
    
    return cache[current][picked] = min;
}
```

## Review
생각보다 쉽게 풀 수 있었던 문제. 경우의 수부터 따져보면 쉽게 풀 수 있을 것 같다.