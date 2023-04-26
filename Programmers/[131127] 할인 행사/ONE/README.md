# [131127] 할인 행사
## Algorithm
- **Sliding Window**

## Logic
- 맵 2개를 사용한다
- 한 개의 맵을 이용해 날짜를 하루씩 뒤로 이동 시키며 물품의 개수를 갱신한 후 want 맵과 비교해 같으면 count++ 한다
```java
for (int day = 0; day < length; day++) {
    sales.put(discount[day], sales.getOrDefault(discount[day], 0) + 1);
    if (day < DAYS - 1) {
        continue;
    }
    if (day - DAYS >= 0) {
        if (sales.get(discount[day - DAYS]) <= 1) {
            sales.remove(discount[day - DAYS]);
        } else {
            sales.put(discount[day - DAYS], sales.get(discount[day - DAYS]) - 1);
        }
    }
    if (wants.equals(sales)) {
        count++;
    }
}
```

## Review
쉬운 문제