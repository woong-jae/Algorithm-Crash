# [77886] 110 옮기기

## Algorithm
- 문자열
- 스택

## Logic

```java
// 모든 110 개수 카운트
for(int i = 0; i < str.length(); i++) {
    stack.push(str.charAt(i));

    if (stack.size() < 3) continue;
    
    char first = stack.pop();
    if(first != '0') {
        stack.push(first);
        continue;
    }
    char second = stack.pop();
    if(second != '1') {
        stack.push(second);
        stack.push(first);
        continue;
    }
    char third = stack.pop();
    if(third != '1') {
        stack.push(third);
        stack.push(second);
        stack.push(first);
        continue;
    }
    strCount++;
}
```

- 스택을 사용해 주어진 문자열에 있는 모든 110의 개수를 카운트한다.

## Review
- 처음 아이디어는 110 을 제거하고, 남은 문자열의 모든 인덱스에 110을 붙여보면서 사전 순 빠른 문자열을 찾으려 했다.
  - 이 아이디어에서, 110이 여러 개 있을 수 있다는 것을 간과했다.
- 110이 여러 개 존재하는 경우와, 0의 뒤에만 110을 붙인다는 조건 하에 구현하다가 시간초과가 나서, 아마 문자열을 붙이는 연산과 110을 찾는 연산때문인 것 같아 다른 방법을 찾아보다가 스택을 함께 사용하는 방법을 택했다.
  - 근데 이 방법은 메모리를 적게 쓰고 시간은 더 쓰는 방법이네 .. ? (스택 pop, push 연산 때문인 것 같다.)
- 시간초과가 발생하는 코드를 줄이기 위해 각 메소드의 시간복잡도를 생각하는 습관을 길러야겠다.