# [16172] 나는 친구가 적다 (Large)

## Algorithm

- Regular Expression
  
## Logic

1. 정규표현식을 통해 S의 숫자를 모두 제거한다.

2. K가 S에 있는지 확인한다.

## Review

최근 정규표현식을 질릴 정도로 사용했기에, 이번 문제에서도 곧바로 정규표현식을 떠올렸다.

그런데 K가 S에 있는지 확인하는 부분에서 `includes` 메서드가 아닌 아래와 같이 정규표현식을 이용했었는데 자꾸 **SyntaxError**가 떴다. 

```js
const pattern = new RegExp(K);
return +pattern.test(S);
```

백준의 node.js에서 해당 문법을 지원하지 않는 것인가..