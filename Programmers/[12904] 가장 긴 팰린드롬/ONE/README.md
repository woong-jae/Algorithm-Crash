# [12904] 가장 긴 팰린드롬
## Algorithm
- **구현**

## Logic
- 가운데가 1개인 경우와 2개인 경우로 생각한다
  - 두 경우 모두 양쪽으로 퍼져 나가며 같은지 검사하고 길이를 갱신한

```java
private int getAnswer(String s, int n, int len, int left, int right) {
    while (left >= 0 && right < n) {
        if (s.charAt(left) != s.charAt(right))
            break;
        len += 2;
        left -= 1;
        right += 1;
    }
    return len;
}
```

## Review
생각보다 쉽게 풀린 문제..인데 이렇게 푸는게 맞나?