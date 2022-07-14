# [92344] A+B

## Algorithm

- Prefix Sum(누적합)

## Logic

- 모든 스킬에 대한 degree 합 즉, 누적합을 위해 0으로 초기화된 N + 1 x M + 1 크기의 2차원 배열(`degreeSum`)을 생성한다.

- skill을 순회히며 `degreeSum[r1][c1]`과 `degreeSum[r2 + 1][c2 + 1]`에는 degree를 더하고, `degreeSum[r2 + 1][c1]`과 `degreeSum[r1][c2 + 1]`에는 -degree를 더한다.

- `degreeSum`에 대해 행을 기준으로 누적합을 시행한 후, 열을 기준으로 누적합을 시행한다.

  - 열을 기준으로 누적합 시행시, 이미 누적합이 완료된 지점에 대해 board와 비교하여 answer를 업데이트한다.

## Review

도저히 아이디어가 떠오르지 않아 다른 사람의 접근법을 참고했다.

2차원 배열 누적합.. 혼자서는 절대 떠올리지 못했을 것이다.

처음 보는 유형의 문제라 재밌었다.