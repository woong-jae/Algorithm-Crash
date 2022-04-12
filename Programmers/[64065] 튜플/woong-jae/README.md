# [64065] 튜플
## Algorithm
- Set
## Logic
주어진 집합의 원소를 길이 순서대로 정렬하면 `s`가 표현하는 튜플을 알아낼 수 있다고 생각했다.

### 1. 문자열을 배열로 파싱
일단 `s`를 정렬하기 위해 문자열을 배열로 파싱했다.

제일 처음과 끝에 있는 중괄호를 먼저 제거하고, 그 다음 중괄호들 안에 있는 문자열을 배열 메서드로 뽑아냈다.
```js
s = s.slice(1, s.length - 1);
let sIndex = 0;
while(sIndex < s.length) {
    const char = s[sIndex++];
    if(char === "{") {
        const begin = sIndex;
        while(s[++sIndex] !== "}");
        setRepresentation.push(s.slice(begin, sIndex).split(",").map(elem => +elem));
    }
}
```

### 2. 정렬하고 튜플 구하기
원소의 길이를 기준으로 오름차순 정렬하고, 집합의 각 원소를 순회하면서 아직 포함되지 않은 요소를 튜플에 추가했다.

이때 요소가 튜플에 있는지 없는지 효율적으로 확인하기 위해 `Set`을 사용했다.
```js
let visited = new Set();
setRepresentation.sort((a, b) => a.length - b.length).forEach(set => {
    for(let i = 0; i < set.length; i++) {
        const element = set[i];
        if(!visited.has(element)) {
            visited.add(element);
            answer.push(element);
            break;
        }
    }
});
```
요소가 튜플에 있는지 없는지 확인하는 부분 때문에 시간복잡도는 O(N^2)이 된다.
## Review
쉽게 풀 수 있었던 문제.

하나 신기했던 것이 있는데, 배열로 파싱할 때 미리 문자열을 숫자로 다 바꿔주는 것이 안한 것보다 빨랐다.
아마 `Set`이 문자열보다는 숫자를 더 효율적으로 처리하는 것 같다.