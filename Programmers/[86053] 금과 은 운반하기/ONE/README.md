# [86053] 금과 은 운반하기
## Algorithm
- **Binary Search**

## Logic
- 시간의 최솟값을 구하는 부분을 시간이 주어졌을 때 금과 은을 a, b 만큼 운반할 수 있는지 확인하는 **이분 탐색** 문제로 생각
- 제한 시간 안에 운반할 수 있는 금의 양을 gold, 은의 양을 silver, 그리고 합을 sum 으로 정의
- moveCount는 해당 시간 동안 왕복 횟수이고 왕복횟수에 w[i]를 곱한 것이 옮길 수 있는 최대의 무게
- 이분 탐색을 하며 주어진 시간에 `gold >= a && silver >= b && sum >= a + b`이 성립 한다면 해당 시간에 옮길 수 있다는 뜻이므로 answer 값을 최신화 하고 왼쪽에서 탐색

```java
while (left <= right) {
    long mid = (left + right) / 2;

    long gold = 0;
    long silver = 0;
    long sum = 0;
    long totalWeight;

    for (int i = 0; i < n; i++) {
        long moveCount = (long) Math.ceil(((double) (mid / t[i]) / 2));
        totalWeight = moveCount * w[i];
        gold += Math.min(g[i], totalWeight);
        silver += Math.min(s[i], totalWeight);
        sum += Math.min(g[i] + s[i], totalWeight);
    }

    if (gold >= a && silver >= b && sum >= a + b) {
        right = mid - 1;
        answer = Math.min(mid, answer);
    }
    else
        left = mid + 1;
}
```

## Review
이분 탐색 문제임은 알았으나 금과 은을 나누어서 구하는 부분이 어려웠고 다른 풀이를 참고하여 작성했다  
풀이에서 수학 벡터 얘기가 나오던데 음 어려웠다...
