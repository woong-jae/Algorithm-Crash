# [72411] 메뉴 리뉴얼
## Algorithm
- Bruteforce
- Combination
## Logic
1. 각 `orders`에 대해 모든 메뉴 조합을 구하고 각 메뉴의 숫자를 센다.

```js
let menu_count = new Map();
    
orders.forEach(order => {
    const allMenu = getCombination(order.split("").sort());
    allMenu.forEach(menu => {
        if(!menu_count.has(menu)) menu_count.set(menu, 0);
        menu_count.set(menu, menu_count.get(menu) + 1);
    });
});
```

2. 각 `courses`의 길이에 따라 메뉴를 필터링하고 가장 큰 카운트 값을 구한다. 그리고 가장 큰 카운트 값을 가진 메뉴만 남게 다시 필터링한다.

```js
return course.map(courseLen => {
    const cand = menu_count.filter(([menu, count]) => menu.length === courseLen && count >= 2)
    const max_count = cand.reduce((prev, cur) => Math.max(prev, cur[1]), 0);

    return cand
        .filter(([_, count]) => count === max_count)
        .map(([menu]) => menu);
}).flat().sort();
```

## Review
처음부터 너무 최적화하면서 풀려고해서 오히려 해맸던 문제다. 미리 선언적으로 구현하고 나중에 최적화하는 방법으로 풀자.
괜히 둘다 잡으려다가 시간도 오래걸리고 문제도 못풀면 낭패다...