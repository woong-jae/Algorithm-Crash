# [77486] 다단계 칫솔 판매

## Algorithm

- Graph

## Logic

`enroll`과 `referral`을 통해 자신의 부모를 가르키는 노드들로 이루어진 그래프를 생성한다.

`seller`의 이름에 해당하는 노드부터 시작해서 발생한 이익을 자손에게 분배한다.
자식이 없거나 더이상 분배할 이익이 없을때까지 분배한다.

```js
amount.forEach((sold, index) => {
  let earned = sold * 100;
  let cur = nodes[name_to_index.get(seller[index])];
  while (cur) {
    if (earned === 0) return;
    const will_earn = Math.ceil(earned * 0.9);
    earned -= will_earn;

    cur.profit += will_earn;
    cur = cur.parent;
  }
});
```

## Review

그림을 보고 트리를 사용하는 문제인 줄 알았다. 하지만 그림을 뒤집어 봐야하는 문제다. 단순히 한 노드부터 아래로 쭉 전파하면 되는 간단한 문제였다.
딱히 어렵지 않은 문제.