# [76502] 괄호 회전하기

## Algorithm
- 스택

## Logic

```java
// 주어진 인덱스 사이의 문자열이 올바른 문자열인지 판단
private boolean isValid(int s, int e) {
    if (s == e) return true;

    for (int i = s; i < e; i++) {
        char ch = str.charAt(i);
        
        if (stack.isEmpty()) {
            if (isCloseCh(ch)) return false;
            stack.add(ch);
        } else {
            char stCh = stack.peek();

            if (isPair(stCh, ch)) stack.pop();
            else if (isCloseCh(ch)) return false;
            else stack.add(ch);
        }
    }
    
    return true;
}
```

- 시작 인덱스와 끝 인덱스를 인자로 받아, 해당 인덱스 사이의 문자열이 올바른지 확인하는 함수
- 스택을 사용해 괄호 문자의 쌍이 맞다면 스택에서 제거하고, 그렇지 않고 닫힌 괄호라면 올바르지 않음을 반환한다.

## Review
- 괄호는 항상 짝이 맞는지에 대한 로직이 필요하고, 이를 구현하기 위해서는 스택이 제격이라 생각했다.
- 금방 풀린 문제