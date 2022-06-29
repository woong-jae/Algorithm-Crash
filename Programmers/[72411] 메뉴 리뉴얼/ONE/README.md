# [72411] 메뉴 리뉴얼
## Algorithm
- **Combination**, **Sorting**

## Logic
- 메뉴의 구성될 개수와 주문의 문자열로 이중반복문 실행
- 개수보다 문자열이 길때만 해당 길의의 문자들을 정렬하여 조합을 실행
- 메뉴 개수만큼의 키들로 가장 많이 주문된 조합을 찾아 answer에 삽입
- 모든 개수를 반복 후 answer을 사전순서로 정렬하면 끝!

```java
for (int num : course) {
    for (String order : orders)
        if (order.length() >= num) {
            ArrayList<Character> list = new ArrayList<>();

            for(int i = 0; i < order.length(); i++)
                list.add(order.charAt(i));

            Collections.sort(list);

            combination(list, new boolean[list.size()], 0, list.size(), num);
        }

    ArrayList<String> keys = new ArrayList<>(map.keySet());
    keys.sort((v1, v2) -> (map.get(v2) - map.get(v1)));

    int maxCount = 0;

    for (String key : keys) {
        if(maxCount > map.get(key))
            break;

        maxCount = map.get(key);

        if(maxCount >= 2)
            answer.add(key);
    }
    map.clear();
}
```

## Review
쪼금 까다롭지만 풀 수 있었던 문제!
