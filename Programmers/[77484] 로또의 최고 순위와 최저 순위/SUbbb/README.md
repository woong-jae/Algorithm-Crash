# [77484] 로또의 최고 순위와 최저 순위

## Algorithm
- 문자열, Map

## Logic

```java
for (int lotto : lottos) {
    if (lotto == 0) zeroCount++;
    else nums[lotto] = true;
}

for (int win : win_nums)
    if (nums[win]) correctCount++;
```

- 숫자의 당첨 유무 확인

## Review
- 쉬운 문제