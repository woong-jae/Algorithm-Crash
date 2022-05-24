# [16472] 고냥이
## Algorithm
- **Two Pointer**

## Logic
- 가장 긴 문자열의 길이를 구하기 위한 `Queue`
- 문자의 종류를 저장할 `Set`
- 해당 문자와 문자의 개수를 저장할 `Map`
- 위의 3가지를 이용 하여 푸는 투 포인터 문제
- 문자열에서 문자를 하나씩 가져 와서 `Queue`, `Map`, `Set`에 각각 넣어 준다
- 만약 `Set`의 크기가 N 보다 크다면 Queue 맨 앞부터 하나씩 제거 하면서
- `Map`에서도 개수를 줄여 주고 만약 개수가 0개가 된다면 `Set`에서 해당 문자를 제거
- 위를 문자의 개수가 N개 이하가 될 때까지 반복
- 그리고 최대의 길이의 `Queue`의 길이만 구해주면 된다

```java
Queue<Character> queue = new LinkedList<>();
Map<Character, Integer> map = new HashMap<>();
Set<Character> set = new HashSet<>();

for (int i = 0; i < len; i++) {
    char c = str.charAt(i);
    queue.add(c);
    map.put(c, map.getOrDefault(c, 0) + 1);
    set.add(c);

    if (set.size() > N) {
        do {
            char head = queue.poll();
            map.put(head, map.get(head) - 1);

            if (map.get(head) == 0)
                set.remove(head);

        } while (set.size() > N);
    }

    if (queue.size() > answer)
        answer = queue.size();
}
```

## Review
카카오에서 풀었던 `보석 쇼핑`의 하위 호환 문제라서 빨리 풀 수 있었다
