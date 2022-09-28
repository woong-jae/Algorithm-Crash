# [1107] 리모컨

## Algorithm

- Bruteforce
- Bitmask

## Logic

만들 수 있는 모든 채널 조합들 중에서 최소 버튼 입력 횟수를 찾아낸다.

못누르는 버튼은 비트마스크로 확인하게 했다.

```js
const isBrokenBtn = (() => {
  const mask = broken_btns.reduce((acc, btn) => acc | (1 << +btn), 0);
  return (btn) => (mask & (1 << btn)) > 0;
})();
```

채널의 자리수는 아무리 커도 6자리이기 때문에 채널이 1,000,000보다 커지면 찾기를 중단한다.

```js
const getMinPress = (channel) => {
  if (channel > 1000000) return Infinity;

  let result = calcPress(channel);
  for (let btn = channel === 0 ? 1 : 0; btn < 10; btn++) {
    if (isBrokenBtn(btn)) continue;
    result = Math.min(result, getMinPress(channel * 10 + btn));
  }
  return result;
};
```

## Review
규칙이 있나 살펴봤지만 예외 사항이 너무 많다고 생각했다. 그렇다면 전부 확인하는 수 밖에 없어서 브루트포스로 구현했다.
다행이 맞긴 했는데, 더 효율적으로 할 수 없을까...
