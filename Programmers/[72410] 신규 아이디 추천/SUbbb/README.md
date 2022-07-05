# [72410] 신규 아이디 추천

## Algorithm
- 문자열

## Logic

```java
private static String replaceStart(String newId, int idx) {
    if (newId.charAt(idx) == '.') return newId.substring(idx + 1);
    return newId;
}

private static String replaceEnd(String newId, int idx) {
    if (idx < 0) return newId;
    if (newId.charAt(idx) == '.') return newId.substring(0, idx);
    return newId;
}
```
- 메소드화하여 역할을 분리했다.

## Review
- 매우 쉬운 문제였다!