# [77486] 다단계 칫솔 판매

## Algorithm
- Disjoint Set

## Logic

```java
private void updateSellerMoney(String p, int money) {
    while(true) {
        if (p.equals("-") || money == 0) break;
        
        int dist = money / 10;
        sellerMoney.put(p, money - dist + sellerMoney.getOrDefault(p, 0));     
        p = parent.get(p);
        money = dist;
    }
}
```

- 자기 자신의 부모를 찾아 이익을 분배하는 함수
- 최종 보스를 만나거나, 더 이상 분배할 이익이 없을 때까지 수행한다.

## Review
- 고문해 시간에 이와 비슷한 개념을 배웠던 것 같아 아이디어 잡는데는 오래 걸리지 않았다.
- 다만 반복을 시작하는 순서를 고민하다가 시간을 많이 날렸다.