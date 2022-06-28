# [60062] 외벽 점검

## Algorithm
- 완전 탐색
- 순열

## Logic

```java
private void canCheck(int[] friends) {
    for (int i = 0; i < weak.length; i++) {
        int start = i;
        boolean canCheck = true;

        for (int idx = 0; idx < friends.length; idx++) {
            for (int j = i; j < i + weak.length; j++) {
                // 두 지점 간 거리가 friends로 뽑힌 친구가 점검 가능한 거리보다 큰 경우는 점검 불가능
                // 따라서 현재 지점을 시작점으로 지정
                if (unrolledWeak[j] - unrolledWeak[start] > friends[idx]) {
                    start = j;
                    idx++;

                    // 현재 지점을 점검할 수 없는데, 더 점검할 친구가 없다면, 종료
                    if (idx == friends.length) {
                        canCheck = false;
                        break;
                    }
                }
            }

            // 점검 완료라면, 더 이상 점검할 필요가 없다. 따라서 flag를 true 지정
            if (canCheck) {
                answer = idx + 1;
                isFinish = true;
                return;
            }
        }
    }
}
```
- 만들어진 점검 인원을 이용해 점검이 가능한지 확인한다.
- 취약 지점 간 거리를 탐색하면서, 모두 점검이 가능하다면 `answer` 는 `idx + 1` 로 업데이트되고, 종료를 위해 `isFinish` 를 업데이트해 순열 만드는 것을 종료한다.
- 점검 인원을 모두 동원해도 점검이 불가능하다면, 더이상 탐색을 종료하고, 다음 점검 인원 순열을 만든다.

## Review
- 순열을 사용해서 1, 2, ... n명으로 가능한지 확인해보는 방식인 것은 쉽게 알았다!
  - 하지만 주어진 인원으로 외벽 점검이 가능한지 확인하는 로직 구현에서 좀 막혔다...
- 구현 연습을 많이 해야겠다.