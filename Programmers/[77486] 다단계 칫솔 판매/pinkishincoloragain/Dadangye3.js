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

console.log(
  solution(
    ["john", "mary", "edward", "sam", "emily", "jaimie", "tod", "young"], // enroll
    ["-", "-", "mary", "edward", "mary", "mary", "jaimie", "edward"], // referral
    ["young", "john", "tod", "emily", "mary"],
    [12, 4, 2, 5, 10]
  )
);
