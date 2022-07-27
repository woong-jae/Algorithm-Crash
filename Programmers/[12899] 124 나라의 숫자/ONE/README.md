# [12899] 124 나라의 숫자
## Algorithm
- **구현**

## Logic
- 수를 3으로 나눈 나머지를 구하고 3으로 나눈다
  - 3으로 나눴을 때의 나머지가 0이었다면 -1 해준다
- StringBuilder의 앞으로 계속 matching 배열의 수를 맞게 넣어준다

```java
public String solution(int n) {
    int num = n;
    int[] matching = {4, 1, 2};
    StringBuilder answer = new StringBuilder();

    while (num > 0) {
        answer.insert(0, matching[num % 3]);
        num /= 3;
        if (answer.charAt(0) == '4')
            num--;
    }
    return answer.toString();
}
```

## Review
능지가 모자라 조금 오래 걸린듯 하다
