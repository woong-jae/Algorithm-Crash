# [16472] 고냥이

## Algorithm
- 투 포인터

## Logic

```java
while(left <= right) {
    // 범위 벗어나면 종료, 기본적으로 right가 먼저 움직임
    if (right >= str.length()) break;

    char rightCh = str.charAt(right);

    if ((left != right || right == 0) && prev == left)
        wordIndexMap.put(rightCh, wordIndexMap.getOrDefault(rightCh, 0) + 1);

    // 아직 N개 이하인 경우, 계속 length를 늘림
    if (wordIndexMap.size() <= N) {
        maxLength = Math.max(maxLength, right - left + 1);
        right++;
        prev = left;
    } else {
        // N개가 채워져 기존의 종류를 하나 제외하는 경우
        char leftCh = str.charAt(left);
        int num = wordIndexMap.get(leftCh) - 1;
        if(num == 0)
            wordIndexMap.remove(leftCh);
        else
            wordIndexMap.put(leftCh, num);
        left++;
    }
}
```

- `Map` 으로 알파벳 종류와 빈도 수를 저장한다.
- `Map` 이 `N` size가 된 경우와 그렇지 않은 경우로 나눠, `right` 를 늘리면서 길이를 증가시키거나 `Map` 에서 기존 알파벳 하나를 지운다.
  - 이때, `left` 에 위치한 문자열에 대해 `Map` 에서 조회해보고, `left` 를 증가시킨다.

## Review
- 투 포인터 알고리즘을 알게 되어 아이디어는 쉽게 잡을 수 있었으나 구현에서 조금 막혔다.
- `right` 를 늘리면서 `prev` 를 확인해주는 과정이 필요했다.