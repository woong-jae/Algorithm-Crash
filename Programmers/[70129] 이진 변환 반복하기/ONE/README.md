# [70129] 이진 변환 반복하기
## Algorithm
- **구현**

## Logic
- 문자열에서 1의 개수를 세어 원래 문자열의 길이에서 뺀것이 0의 개수이기 때문에 answer[1]에 더하고  
- 그 길이를 다시 이진수의 문자열로 만들고  
- 위를 문자열이 "1"이 될 때까지 반복

```java
while (!s.equals("1")) {
    len = s.codePoints()
            .filter(c -> c == '1')
            .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
            .length();

    answer[0]++;
    answer[1] += s.length() - len;

    s = Integer.toBinaryString(len);
}
```

## Review
스트림은 재밌다!
