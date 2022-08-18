# [2800] 괄호 제거

## Algorithm

- Stack
- Combination
- DFS

## Logic

1. Stack을 사용해 모든 괄호쌍을 구한다.

```js
const stack = [];
for (let i = 0; i < s.length; i++) {
  const char = s[i];
  if (char === "(") {
    stack.push(i);
  } else if (char === ")") {
    const front = stack.pop();
    braces.push([front, i]);
  }
}
```

2. DFS를 통해 모든 경우의 수를 구한다.

```js
const combinations = [];
const dfs = (i, picked) => {
  if (i >= braces.length) {
    combinations.push([...picked]);
    return;
  }

  picked.push(i);
  dfs(i + 1, picked);
  picked.pop();

  dfs(i + 1, picked);
};
dfs(0, []);

combinations.pop();
```

3. 강 경우의 수에 해당하는 문자열을 생성하고, 중복 제거와 정렬을 수행한다.

```js
const removeBrace = (s, braceIndexes) => {
  s = s.split("");
  braceIndexes.forEach((index) => {
    const [front, back] = braces[index];
    s[front] = null;
    s[back] = null;
  });
  return s.filter((char) => char).join("");
};

return Array.from(new Set(combinations.map((braceIndexes) => removeBrace(s, braceIndexes))))
  .sort()
  .join("\n");
```
## Review
중복 제거하는 엣지 케이스를 생각 못해서 해맸다... 역시 백준 문제는 프로그래머스와는 좀 다르다. 살짝 불친절한 느낌.