# [92342] 양궁대회
## Algorithm
- **Combination**

## Logic
- 중복 조합을 이용해 라이언이 쏠수 있는 경우의 수를 완전탐색으로 구한다
- 구한 라이언의 점수 배열로 어피치와 비교하며 가장 점수 차이가 크게 나는 배열을 구한다
- 가장 낮은 점수를 많이 쏘는 경우의 수부터 구하기 때문에 조건을 만족

```java
private void dfs(int start, int index, int[] combination) {
    if (index == n) {
        int[] tmpLion = new int[11];

        for(int score : combination)
            tmpLion[10 - score]++;

        int lionScore = 0;
        int apeachScore = 0;

        for (int i = 0; i < 11; i++) {
            int score = 10 - i;

            // 라이언이 이긴 경우
            if (tmpLion[i] > apeach[i])
                lionScore += score;
                // 어피치가 이긴경우
            else if (tmpLion[i] < apeach[i])
                apeachScore += score;
                // 둘 다 과녁에 1개이상 맞추고 동점일 경우
            else if (apeach[i] != 0 && tmpLion[i] == apeach[i])
                apeachScore += score;
        }

        if (lionScore - apeachScore > maxDiff) {
            maxDiff = lionScore - apeachScore;
            lion = tmpLion;
        }
        return;
    }

    for (int i = start; i < 11; i++) {
        combination[index] = i;
        dfs(i, index + 1, combination);
    }
}
```

## Review
아이디어를 생각하는 데 너무 오래 걸렸고  
전혀 2레벨 문제라는 생각이 들지 않았다  
어려웠던 문제
