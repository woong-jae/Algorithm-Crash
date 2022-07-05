# [72412] 순위 검색
## Algorithm
- Tree
- Binary search

## Logic
1. `info`의 정보로 트리를 구성한다. 이때 말단 노드는 배열로 두어서 모든 점수를 담고, 마지막에 정렬을 해 이분 탐색을 사용할 수 있게 한다.

```js
const root = new Map();
const leaf = new Set();

info.forEach(information => {
    let cur = root;
    information = information.split(" ");
    for(let i = 0; i < 4; i++) {
        const elem = information[i];
        if(!cur.has(elem)) cur.set(elem, i < 3 ? new Map() : []);
        cur = cur.get(elem);
    }
    leaf.add(cur);
    cur.push(+information[4]);
});
leaf.forEach(arr => arr.sort((a, b) => a - b));
```

2. `query`의 각 쿼리에 따라 트리를 탐색한다. 말단 노드에 도달하면 이분 탐색을 이용해 현재 브랜치의 해당하는 지원자 수를 구한다.

```js
const countCandiate = (queries) => {
        
    const search = (cur, query_index) => {
        let query = queries[query_index];
        if(query_index === queries.length - 1) {
            let left = 0, right = cur.length - 1;
            
            while(left <= right) {
                const mid = Math.floor((left + right) / 2);
                if(cur[mid] < query) left = mid + 1;
                else right = mid - 1;
            }
            
            return cur.length - left;
        }
        
        if(query === "-") {
            let count = 0;
            cur.forEach(next => count += search(next, query_index + 1));
            return count;
        }
        if(!cur.has(query)) return 0;
        
        return search(cur.get(query), query_index + 1);
    }
    
    return search(root, 0);
}
```

## Review
처음에 말단 노드도 `Map` 자료구조로 한 다음 필터링하는 방식을 사용해 시간초과가 났다.
시간을 줄일만한 곳은 필터링하는 부분이라고 생각했고 이분 탐색을 적용해 풀 수 있었다.
트리를 구성하는데 좀 버벅여서 시간이 걸렸다.