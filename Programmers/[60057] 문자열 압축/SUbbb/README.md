# [60057] 문자열 압축

## Algorithm
- 문자열

## Logic

```java
// 전체 반복은 s 길이의 절반까지만
for (int len = 1; len <= inputLength / 2; len++) {
    int length = 0;
    int idx = 0;

    // 자를 word가 s의 범위 안에 있는 경우까지 반복
    while(idx + len <= inputLength) {
        String word = getWord(idx, len);
        int count = 0;
        
        // 확인할 문자열이 s의 범위 안에 있고, 그 문자열이 word와 동일한 경우
        // 그 문자열의 개수를 구한다.
        while (idx + len <= inputLength && word.equals(getWord(idx, len))) {
            idx += len;
            count++;
        }
        // count가 1이면 문자열 길이만 더하고, 1보다 큰 경우에는 그 길이도 함께 더한다.
        length += word.length() + (count > 1 ? String.valueOf(count).length() : 0);
    }
    // 남은 문자열 붙이기
    length += inputLength - idx;
    minLength = Math.min(minLength, length);
}
```

- 문자열은 **1부터 주어진 문자열 길이의 절반**까지만큼 자르는 것을 반복한다.
- 정한 길이만큼 문자열을 비교하고 개수와 정한 길이만큼을 `length` 에 더해, 최종적으로 `minLength` 를 최신화한다.

## Review
- 제일 먼저 그리디한 방법을 떠올리긴 했으나, 시간 초과가 나지 않을까 생각했다.
- 문자열을 길이가 최대 1000이었고, 어차피 문자열 비교를 위해 절반 길이까지만 자를 것이므로 괜찮을 것 같았다.