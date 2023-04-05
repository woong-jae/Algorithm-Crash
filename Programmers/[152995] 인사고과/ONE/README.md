# [152995] 인사고과
## Algorithm
- **Sorting**

## Logic
- 근무 태도 점수를 내림차순, 동료평가 점수는 오름차순 정렬한다
  - 이렇게 한 후, 최대 동료평가 점수를 계속 갱신하며
  - 최대 동료 평가 점수보다 낮은 사원은 인센티브에서 자동으로 탈락하게 된다
  - 동료 평가 점수가 오름차순인 이유는 동일한 근무 태도 점수인 경우에 적용되기 위함
- 점수의 합으로 내림차순 정렬 후 완호의 점수에 해당하는 등수를 찾아 반환

```java
List<Score> scoreList = new ArrayList<>();

int[] wonho = scores[0];
Arrays.sort(scores, (a, b) -> a[0] == b[0] ? a[1] - b[1] : b[0] - a[0]);

int maxReviewScore = 0;
for (int[] score : scores) {
    if (maxReviewScore > score[1]) {
        if (Arrays.equals(score, wonho)) {
            return -1;
        }
        continue;
    }
    maxReviewScore = score[1];
    scoreList.add(new Score(score[0], score[1],
            Arrays.equals(score, wonho)));
}
scoreList.sort(Comparator.comparingInt(Score::sumOfScore).reversed());

return (int) scoreList.stream()
        .takeWhile(score -> !score.isWonho())
        .count() + 1;
```

## Review
정렬 부분에 대해서 생각하는게 오래 걸렸던 것 같다!  
조금 생각이 필요한 문제
