# [72414] 광고 삽입

## Algorithm
- 구간 합
- 슬라이딩 윈도우

## Logic

```java
// 초기값 설정 !
// adT만큼의 구간 합을 구하고 이보다 큰 구간 합을 찾기 위해서
long sum = 0;
for (int i = 0; i < adT; i++)
    sum += viewerCount[i];
long maxSum = sum;

int startT = 0;
for (int i = adT; i < playT; i++) {
    // adT 만큼의 구간을 확인하기 위해 제일 앞 값을 빼고, 더한다.
    int front = i - adT;
    sum -= viewerCount[front];
    sum += viewerCount[i];

    // 구간 합이 가장 큰 경우, 갱신
    if (sum > maxSum) {
        maxSum = sum;
        // front는 현재 포함되지 않는 시간이므로 + 1 해야 시작 시간
        startT = front + 1;
    }
}
```
- 광고 시간만큼 앞에서부터 구간 합을 구한다.
- 이를 초기값으로 설정해, 이후부터 슬라이딩 윈도우 방식으로 광고 시간 크기만큼 구간 합을 갱신하면서, 최대가 되는 경우의 시작시간을 구한다.

## Review
- `String` 을 `int` 형으로 변환해 초 단위로 저장하는 것은 바로 생각했고, 처음에는 시작시간과 종료시간, 러닝 타임을 기준으로 정렬하고 이를 다시 탐색하면서 각 구간별 뷰어를 구하려 했다.
- 구현에서 막히고, 좀 더 효율적인 방법이 없을까해서 다른 풀이를 확인했더니 구간 합과 슬라이딩 윈도우를 활용하는 문제였다는 것을 알았다.