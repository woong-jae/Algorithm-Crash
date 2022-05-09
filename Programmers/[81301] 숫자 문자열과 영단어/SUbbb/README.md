# [81301] 숫자 문자열과 영단어

## Algorithm
- 문자열, Map

## Logic

```java
while(idx < s.length()) {
    char ch = s.charAt(idx);
    if (Character.isDigit(ch)) {
        answer.append(ch);
        idx++;
    } else {
        for (String key : map.keySet()) {
            if (s.contains(key)) {
                int start = s.indexOf(key);
                if (start != idx) continue;
                s = s.substring(0, start) + s.substring(start + key.length());
                answer.append(map.get(key));
                break;
            }
        }
    }
}
```

- 영단어와 그에 해당하는 숫자를 Key & Value로 저장한 `map` 을 사용한다.
- `idx` 에 해당하는 문자를 읽었을 때, 숫자가 아닌 경우 `map` 에서 해당 영단어를 찾아 그 길이만큼 `s` 에서 제거하고, `answer` 에 영단어에 해당하는 숫자를 추가한다.

## Review
- 간단한 문자열 문제였고, 처음에는 단순히 영단어를 저장한 `table` 배열을 탐색하는 방식으로 해결했다가, 이후 `Map` 을 사용해 탐색하는 방식으로 조금 더 시간을 줄일 수 있었다.
- 정규식으로도 해결할 방법이 있을테지만, 문법이 바로 떠오르지 않아 사용하지 않았다.
- 문자가 읽힌 경우, `map` 을 모두 탐색하지 않는 방법을 고민해봐야 한다.