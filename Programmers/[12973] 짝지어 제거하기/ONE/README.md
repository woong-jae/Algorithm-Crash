# [12973] 짝지어 제거하기
## Algorithm
- **Stack**

## Logic
- 짝지어 2개씩 제거하기 때문에 스택을 사용하여 문자열에서 문자를 한개씩 비교하며  
- 만약 스택의 제일 위와 새로 넣는게 같다면 pop()
- 아니면 그냥 add() 해준다

```java
for (int i = 0; i < n; i++) {
    if (!stack.isEmpty() && stack.peek() == s.charAt(i)) {
        stack.pop();
        continue;
    }
    stack.add(s.charAt(i));
}
```

## Review
처음에는 연속된 문자 2개를 replace 하는 것을 생각했으나 정확도에서 시간이 네자리나 나오는 것을 보고  
시간 복잡도를 O(N)안으로 해결해야된다는 생각이 들었고 2개씩 짝을 짓기 때문에 스택을 생각해내어 풀었다