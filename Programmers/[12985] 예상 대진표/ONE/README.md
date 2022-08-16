# [12985] 예상 대진표
## Algorithm
- **구현**

## Logic
- 한 경기가 치뤄질 때마다 2배씩 줄어들게 된다
- a,b 둘다 +1 한 후에 /2 하게 되면 다음 경기의 번호가 된다
- 위를 각각 +1 한것의 /2가 같아질 때까지 반복

```java
public int solution(int n, int a, int b)
{
    int answer = 1;

    while ((a + 1) / 2 != (b + 1) / 2) {
        a = (a + 1) / 2;
        b = (b + 1) / 2;
        answer++;
    }
    return answer;
}
```

## Review
쉬운문제
