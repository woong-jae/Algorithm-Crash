## :mag: Algorithm

String

## :round_pushpin: Logic

```js
for (const ch of s.slice(1, s.length - 1)) {
  switch (ch) {
    case '}':
      sumList.push(sum + +num);
      break;
    case '{':
      sum = 0;
      break;
    case ',':
      sum += +num;
      num = '';
      break;
    default:
      num += ch;
  }
}
```

- 주어진 문자열을 한 문자씩 순회하며, 같은 집합 내 있는 숫자를 더해 배열 `sumList`에 `push` 한다.

```js
sumList.sort((a, b) => a - b);

for (let i = 0; i < sumList.length; i++) {
  let element = i ? sumList[i] - sumList[i - 1] : sumList[i];
  answer.push(element);
}
```

- `sumList` 를 오름차순으로 정렬하고, 반복문을 통해 `sumList` 원소 간의 차를 `answer`에 `push` 한다.

<br />

**시간 복잡도:** `sort` 메서드를 사용헀기에 **O(NlogN)**

## :memo: Review

같은 집합 내 원소들을 더한 값의 차를 이용하는 방법을 바로 떠올려 해결할 수 있었다.

다만, 문자열 순회하는 부분에서 정렬을 수행하도록 해주고 싶었지만 그러지 못했다.