# [77484] 로또의 최고 순위와 최저 순위
## Algorithm
- 구현
## Logic
당첨 번호와 비교해 일치하는 것의 개수와 알아볼 수 없는 번호의 개수를 구한다.

당첨번호의 각 번호를 46인 배열에 해당하는 인덱스에 카운트해서 중복되는 번호까지 다룰 수 있게 한다.
구매한 로또와 앞에서부터 비교하면서 번호에 해당하는 인덱스의 카운트 값이 0보다 크면 일치하는 것이라고 판단할 수 있다.

```js
const win_picks = Array(46).fill(0);
win_nums.forEach(num => win_picks[num]++);

const unknown = lottos.reduce((prev, cur) => prev + (cur === 0 ? 1 : 0), 0);
const match = lottos.reduce((prev, cur) => {
    if(cur > 0 && win_picks[cur] > 0) {
        win_picks[cur]--;
        prev += 1;
    }
    return prev;
}, 0);
```

일치하는 개수와 알아볼 수 없는 개수로 최소 순위와 최대 순위를 계산하면 된다.

## Review
쉬운 문제.