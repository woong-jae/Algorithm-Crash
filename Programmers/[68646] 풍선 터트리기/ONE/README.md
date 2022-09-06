# [68646] 풍선 터트리기
## Algorithm
- **구현**

## Logic
- 좌, 우 방향으로 각각 해당 방향으로 진행되는 인덱스에 최솟값을 저장한다
  - 예를 들어 예시의 {-16,27,65,-2,58,-92,-71,-68,-61,-33} 와 같은 배열은
  - 좌 : {-16,-16,-16,-16,-16,-92,-92,-92,-92,-92}
  - 우 : {-92,-92,-92,-92,-92,-92,-71,-68,-61,-33}
- 좌, 우 끝값은 무조건 터트릴 수 있으므로 answer = 2 부터 시작하여
  - a[i](i = 1 ~ n-2) 일 때 leftMin[i - 1], right[i - 1] 보다 둘다 작은 경우에는 터뜨릴 수 없다 (본인보다 작은 풍선을 터트릴 기회는 1번)

```java
public int solution(int[] a) {
    int n = a.length, answer = 2;
    int[] leftMin = Arrays.copyOf(a, n);
    int[] rightMin = Arrays.copyOf(a, n);

    for (int i = 1; i < n; i++) {
        leftMin[i] = Math.min(leftMin[i - 1], leftMin[i]);
        rightMin[n - 1 - i] = Math.min(rightMin[n - 1 - i], rightMin[n - i]);
    }

    for (int i = 1; i < n - 1; i++) {
        if (a[i] > leftMin[i - 1] && a[i] > rightMin[i + 1])
            continue;
        answer++;
    }

    return answer;
}
```

## Review
처음에는 O(N²)의 시간복잡도로 구현을 해서 시간초과가 계속나서 시간을 줄일 방법을 생각하다가  
미리 양쪽 방향의 최솟값 배열을 만들면 될 것 같아서 구현하여 통과했다  
약간의 능지 문제