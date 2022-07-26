# [60062] 외벽 점검

## Algorithm

- brute force

## Logic

```js
const circularWeak = [...weak, ...weak.map((x) => x + n)];

circularWeak.pop();
```

**1. weak를 원형으로 표현한다.**

- ex) `[1, 5, 6, 10]` -> `[1, 5, 6, 10, 13, 17, 18]`

<br />

```js
dist.sort((a, b) => b - a);
```

**2. dist를 내림차순 정렬한다.**

- 이동할 수 있는 거리가 큰 친구부터 취약 지점 점검을 수행하도록 하기 위함이다.

<br />

```js
// 최소 인원부터 최대 인원까지 모든 경우의 수
for (let count = 1; count <= dist.length; count++) {
  // 해당 인원들로 접근 가능한 모든 경우의 수
  for (const permu of getPermutation(dist, count)) {
    for (let start = 0; start < weak.length; start++) {
      // 첫번째 취약 지점부터 유효한 범위만 채택
      let linearWeak = circularWeak.slice(start, start + weak.length);
      // 점검 수행
      for (const p of permu) {
        const checkRange = linearWeak[0] + p;
        // 점검 완료한 지점은 제외
        linearWeak = linearWeak.filter((w) => w > checkRange);
        // 모든 취약 지점 점검 완료
        if (!linearWeak.length) {
          return count;
        }
      }
    }
  }
}
```

**3. 모든 경우의 수로 취약 지점 점검을 수행한다.**

- (주석 참고)

- 최소 인원부터 최대 인원까지 점검에 투입하여 성공할 시 해당 인원(`count`)을 반환한다.

- 이때, 선정한 인원들의 모든 순서를 고려하기 위해 순열을 통한 모든 경우의 수를 확인한다.

### 시간 복잡도: O(N!)

## Review

제한사항을 보고 순열을 이용하는 완전탐색임을 알았지만, 해결법을 스스로 찾지 못했다.

결국 다른 사람의 코드를 참고했고, week를 원형으로 추상화한 1차원 배열을 이용하는 방법을 알게되어 해결할 수 있었다.

이번주 문제들은 꽤 힘들다..
