# [60058] 괄호 변환

## Algorithm
- 문자열

## Logic

```java
private String recursive(String p) {
    // 빈 문자열인 경우 반환
    if (Objects.equals(p, "")) return p;
    
    // u와 v로 분리
    String[] uv = splitString(p);
    String u = uv[0];
    String v = uv[1];
    
    // u가 올바르다면, v에 대해 recurvie()한 결과를 붙여 반환
    if (isProper(u)) return u + recursive(v);
    
    // u가 올바르지 않은 경우
    StringBuilder sb = new StringBuilder("(");
    sb.append(recursive(v)).append(")").append(reverseBracket(u.substring(1, u.length() - 1)));
    return sb.toString();
}
```

- 주어진 "균형잡힌 괄호 문자열"을 "올바른 괄호 문자열"로 변환하는 함수

## Review
- 하라는 대로만 구현하면 쉽게 풀리는 문제!
- 어떤 함수를 필요로 하는지 나누어 생각하고 구현해보는 연습하기에 좋은 문제였다.