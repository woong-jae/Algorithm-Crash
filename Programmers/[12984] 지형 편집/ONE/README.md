# [12984] 지형 편집
## Algorithm
- **파라메트릭 서치**

## Logic
- 배열의 높이 중 최솟값과 최댓값을 찾아 파라메트릭 서치를 진행한다
  - 코스트의 증감을 파악하기 위해 mid 와 mid + 1의 높이에서 비용을 구한다
  - 비용이 같을 경우 반복을 종료하고
  - 높이가 증가할수록 비용이 증가할 경우 왼쪽에서
  - 높이가 증가할수록 비용이 감소할 경우 오른쪽에서 탐색한다

```java
while (start <= end) {
    mid = (start + end) / 2;

    if (start == end)
        break;

    long cost1 = getCost(mid, land, P, Q);
    long cost2 = getCost(mid + 1, land, P, Q);

    if (cost1 < cost2)
        end = mid;
    else
        start = mid + 1;
}
```

## Review
내 풀이 방식에서 스트림을 이용하여 풀었었는데 자꾸 효율성 4번에서 시간 초과가 발생했다  
분명히 높이 2차원 배열에서 값을 distinct 로 오름차순 정렬하여 구하는 방식이 시간이 덜 걸릴것 같았는데  
바꾸는 방식에서 시간초과가 발생하나보다 거의 다 풀었는데 까비!
