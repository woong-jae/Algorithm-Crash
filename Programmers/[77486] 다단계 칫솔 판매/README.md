# [77486] 다단계 칫솔 판매
## Algorithm
- 구현
## Logic
- Tree 구현
- 하위 node를 잡고 아래에서부터 위로 탐색하며 share을 합한다.
- 
```javascript
function solution(enroll, referral, seller, amount) {
  var answer = [];
  let map = new Map();
  for (let i = 0; i < enroll.length; i++) {
    map.set(enroll[i], { parents: referral[i], sales: 0 });
  }
  for (let i = 0; i < seller.length; i++) {
    let money = amount[i] * 100;
    let sell = seller[i];

    while (true) {
      let data = map.get(sell);
      let share = Math.floor(money * 0.1);

      map.set(sell, {
        parents: data.parents,
        sales: data.sales + money - share,
      });

      if (data.parents === "-") break;
      if (share === 0) break;
      sell = data.parents;
      money = share;
    }
  }

  for (let [key, value] of map) {
    answer.push(value.sales);
  }

  return answer;
}

```

## Review
Tree인 건 문제를 보고 알았다.
js로 Tree로 구현하는 게 감이 안와서 어떻게든 하다가.. 마지막 테스트케이스 3개가 계속 시간초과되어서 트리 구현에 문제가 있는 걸 알았다.
Tree 구조 구현할 때 객체 생성하는 느낌으로 섞어서 자기 Referral을 구현하면 구하기 편하다.

