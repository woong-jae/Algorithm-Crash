# [72411] 메뉴 리뉴얼

## Algorithm

- Brute force (Combination)

- Sort

- Map

## Logic

```js
const menuMap = new Map();

orders.forEach((order) => {
  const orderArr = order.split('').sort();
  course.forEach((n) => {
    getCombination(orderArr, n).forEach((combi) => {
      const menu = combi.join('');
      if (menuMap.has(menu)) {
        menuMap.set(menu, menuMap.get(menu) + 1);
        return;
      }
      menuMap.set(menu, 1);
    });
  });
});
```

**1. 주어진 orders를 순회하며 각 원소에 대한 조합을 Map에 추가한다.**

- orders의 각 원소, 즉 문자열을 배열로 변환한 뒤 해당 배열에 대한 조합을 생성한다.

- 생성한 조합을 순회하며 각 원소, 즉 배열을 문자열로 변환한다.

- 이를 key로 해당 문자열의 개수를 value로 하여 Map에 추가 및 업데이트한다.

<br />

```js
const selectedMenuMap = new Map();

for (const [menu, count] of menuMap.entries()) {
  if (count === 1) {
    continue;
  }

  const key = menu.length;

  if (selectedMenuMap.has(key)) {
    const { maxCount, menus } = selectedMenuMap.get(key);
    if (maxCount < count) {
      selectedMenuMap.set(key, {
        maxCount: count,
        menus: [menu],
      });
    } else if (maxCount === count) {
      menus.push(menu);
    }
    continue;
  }

  selectedMenuMap.set(key, {
    maxCount: count,
    menus: [menu],
  });
}
```

**2. 1에서 생성한 Map을 순회하며 단품 메뉴의 개수(course)를 key로 하는 새 Map을 생성한다.**

- 이때, value는 `{ maxCount: 가장 많은 주문 횟수, menus: 메뉴 구성 문자열을 원소로 한 배열}` 형식의 객체이다.

<br />

```js
const answer = [];

for (const { menus } of selectedMenuMap.values()) {
  answer.push(...menus);
}

return answer.sort();
```

**3. 2에서 생성한 Map을 순회하며 각 value의 `menus` 배열의 원소들을 `answer`에 삽입한 후, 정렬하여 반환한다.**

### 시간 복잡도 : O(2^N)

## Review

주어지는 orders의 각 문자열의 크기가 10 이하인 것을 보고 조합을 쓰면 되겠다 생각했다.