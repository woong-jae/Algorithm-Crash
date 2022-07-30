# [86053] 금과 은 운반하기

## Algorithm
- 이분 탐색

## Logic

```java
for (i = 0; i < n; i++) {
    // 초기화
    gCur = g[i];
    sCur = s[i];
    wCur = w[i];
    tCur = t[i];
    gsCur = gCur + sCur;
    
    // 왕복 횟수
    roundCnt = (long) Math.ceil((double) (T / tCur) / 2);
    // 총 광물 무게 = 왕복 횟수 * 한 번에 운반 가능한 광물 무게
    wAll = roundCnt * wCur;
    
    // 각 도시에서 운반한 금의 무게
    gMax += Math.min(gCur, wAll);
    // 각 도시에서 운반한 은의 무게
    sMax += Math.min(sCur, wAll);
    // 각 도시에서 운반한 금과 은의 무게
    gsMax += Math.min(gsCur, wAll);
}
```

- 운반 가능한 금, 은, 금과 은의 총 무게를 계산한다.

```java
if (a <= gMax && b <= sMax && ab <= gsMax) {
    right = T - 1;
    answer = T;
} else left = T + 1;
```

- 운반 가능한 금, 은, 금과 은의 양을 통해 광물을 모두 옮길 수 있는지 판단한다.
- 주어진 시간 내에 금과 은에 대해서는 운반한다해도, 총 필요한 광물 양을 만족하지 못하는 경우는 운반할 수 없으므로, 이에 대한 확인이 필요하다.

## Review
- 진짜 몇번을 봐도 풀이가 어렵다. 잘 이해가 안된다 ...