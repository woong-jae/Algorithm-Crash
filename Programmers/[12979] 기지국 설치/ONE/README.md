# [12979] 기지국 설치
## Algorithm
- **구현**

## Logic
- 처음 시작을 1로 하고 스테이션 간 공간의 거리를 구한다
  - 구한 공간의 거리를 기지국 범위를 가지고 놓아야 할 개수를 구한다
    - 공간을 기지국 범위로 나눈 몫에 나머지가 있다면 1개를 더 추가한다

```java
public int solution(int n, int[] stations, int w) {
    int start = 1, boundary = 1 + w * 2, answer = 0;

    for (int station : stations) {
        int stationStart = station - w;
        if (stationStart > start)
            answer += countStation(stationStart - start, boundary);
        start = station + w + 1;
    }

    if (start <= n)
        answer += countStation(n - start + 1, boundary);

    return answer;
}

private int countStation(int n, int boundary) {
    int count = n / boundary;
    if (n % boundary != 0)
        count += 1;
    return count;
}
```

## Review
오름차순이 키포인트였던 문제!
