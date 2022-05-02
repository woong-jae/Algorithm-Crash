# [81301] 숫자 문자열과 영단어
## Algorithm
- 없음
## Logic
- 문자를 하나씩 검사하여 숫자면 `StringBuilder`에 바로 넣어주고
- 숫자가 아니면 tmp 에 하나씩 넣어서 숫자랑 연관된 단어면 `StringBuilder`에 숫자를 넣고 tmp를 비워줌

```java
for (char c : s.toCharArray()) {
    if (c >= '0' && c <= '9')
        sb.append(c);

    else {
        tmp.append(c);

        if (map.containsKey(tmp.toString())) {
            sb.append(map.get(tmp.toString()));
            tmp.delete(0, tmp.length());
        }
    }
}
```

## Review
쉬운 문제
