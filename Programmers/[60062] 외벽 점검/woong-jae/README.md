# [60062] 외벽 점검
## Algorithm
- Bruteforce
- Permutation
- Binary Search
## Logic
모든 지점에서 탐색을 위해 원형을 선형으로 펴준다.
```js
weak = weak.concat(weak.map(point => point + n));
```

가능한 모든 친구들의 순서는 순열을 통해 구하고,
각 지점에 가능한 모든 친구들의 순서를 테스트 해본다.

```js
dist = getPermutation(dist);
// ...
dist.forEach(step => {
    for(let i = 0; i < weakLength; i++) {
        let start = weak[i];
        let end = weak[i + weakLength - 1];
        for(let d = 0; d < step.length; d++) {
            start += step[d];
            if(start >= end) {
                answer = Math.min(answer, d + 1);
                break;
            }
            let next = getUpperBound(weak, start);
            start = weak[next];
        }
    }
});
```

각 지점에서 다음 스탭을 더했을 때 끝까지 커버가 된다면 모든 취약점을 검사한 것이다.

아니라면 다음 시작점에서 검사를 다시한다. 이때 다음 시작점을 찾기 위해 `start + step[d]`보다 큰 `weak`의 인덱스를 찾기 위해 이분탐색을 사용한다.

## Review
그리디로 접근했다가 혼났다. 전혀 모르겠어서 다른 사람들의 풀이를 보고 풀었다.

이런 식의 완전탐색 문제는 처음이여서 풀이를 못찾은 것 같다. 너무 어렵다...