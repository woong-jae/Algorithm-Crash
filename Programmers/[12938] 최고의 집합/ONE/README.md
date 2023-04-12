# [12938] 최고의 집합
## Algorithm
- **단순 구현**

## Logic
- 합이 S가 되고 곱을 최대로 하는 N개의 숫자들을 구하는 것
- 곱이 최대가 되는 것은 각 숫자간의 차이가 최소가 되어야 한다
- 합 S를 N으로 나누고 나머지를 1씩 뒤에서 부터 더하면 곱이 최대가 된다

```java
int[] answer = new int[n];
int basic = s / n;
int remain = s % n;

for (int index = 0; index < n; index++) {
    answer[index] = basic;
    if (index >= n - remain) {
        answer[index]++;
    }
}
```

## Review
쉬운 문제!
