# [86053] 금과 은 운반하기
## Algorithm
- Binary search
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
사실 처음엔 Greedy인 줄 알았다가 생각을 조금 더 해서 Knapsack인 줄 알고 구현했다.
Knapsack으로 구현하다가 점화식을 아무리 해도 정확성 테스트에서 틀려서 잘못된 방법인 걸 알았다.
결국 풀이를 보고 Binary search인 걸 알고 나서 알고리즘에 익숙하지 않은 걸 알았다..

점화식을 세우는 대신에 10e5 * 4 * 10e9로 end를 잡고 min이랑 max 사이를 binary search하면서 값을 찾는 걸 보고 좀 놀랐다.
아마 금과 은을 나르는 버스 시간이 정수로 정해져서 그렇게 되는 것 같다.
문제를 보고 어떤 알고리즘으로 구현해야 하는지 찾는 눈을 길러야겠다..