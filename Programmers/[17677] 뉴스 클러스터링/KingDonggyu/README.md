# [17677] 뉴스 클러스터링

## Algorithm

- Map

- Regular expression

## Logic

```js
for (let i = 0; i < Math.max(str1.length, str2.length) - 1; i++) {
  if (i < str1.length - 1) {
    updateMap(str1, i, true);
  }

  if (i < str2.length - 1) {
    updateMap(str2, i, false);
  }
}
```

- 두 문자열에 대해 한번에 map을 업데이트 한다.

- (map을 업데이트 하는 함수는 아래와 같다.)

```js
const updateMap = (str, i, isStr1) => {
  const element = str[i] + str[i + 1];

  // pattern = /^[a-zA-Z]*$/;
  if (!pattern.test(element)) {
    return;
  }

  if (map.has(element)) {
    const count = map.get(element);
    map.set(element, {
      str1: isStr1 ? count.str1 + 1 : count.str1,
      str2: isStr1 ? count.str2 : count.str2 + 1,
    });
    return;
  }

  map.set(element, {
    str1: isStr1 ? 1 : 0,
    str2: isStr1 ? 0 : 1,
  });
};
```

- 두 글자인 원소를 생성하고, 정규 표현식을 통해 영문자로만 이루어져 있는지 확인한다.

- `{ 원소: { str1: str1의 해당 원소 개수, str2: str2의 해당 원소 개수 } }` 형태로 map을 업데이트한다.

```js
let union = 0;
let intersection = 0;

for (const count of map.values()) {
  union += Math.max(count.str1, count.str2);
  intersection += Math.min(count.str1, count.str2);
}

if (!union && !intersection) {
  return 65536;
}

return Math.floor((intersection / union) * 65536);
```

- 두 문자열에 대한 map 업데이트가 끝나면, 합집합과 교집합의 크기를 구한다.

### 시간 복잡도 : O(N)

## Review

문제를 읽자마자 map을 이용한 아이디어를 떠올렸고, 쉽게 해결했다.
