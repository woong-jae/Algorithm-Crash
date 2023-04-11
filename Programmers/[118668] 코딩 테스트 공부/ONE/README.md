# [118668] 코딩 테스트 공부
## Algorithm
- **Dijkstra**

## Logic
- 이번 문제는 크게 두 가지(DP, Dijkstra)로 풀이할 수 있었는데 나는 Dijkstra 알고리즘으로 풀었다

- 소요 시간을 기준으로 오름차순 정렬하는 우선순위큐를 사용
  - 일반적인 다익스트라 처럼 소요시간이 최소로 걸리는 부분을 찾아 소요시간을 반환
  - visited[알고력][코딩력]은 최단시간에 한번 방문할 때 true 로 해서 중복해서 방문하는 것을 줄인다

```java
private int dijkstra(int algoPower, int codingPower) {
    int targetAlgoPower = Arrays.stream(problems)
            .mapToInt(problem -> problem[0])
            .max()
            .getAsInt();
    int targetCodingPower = Arrays.stream(problems)
            .mapToInt(problem -> problem[1])
            .max()
            .getAsInt();
    boolean[][] visited = new boolean[500][500];

    Queue<Power> powers = new PriorityQueue<>(Comparator.comparing(Power::time));
    powers.add(new Power(algoPower, codingPower, 0));

    while (!powers.isEmpty()) {
        Power current = powers.poll();

        if (visited[current.algoPower()][current.codingPower()]) {
            continue;
        }
        visited[current.algoPower()][current.codingPower()] = true;

        if (current.algoPower() >= targetAlgoPower && current.codingPower() >= targetCodingPower) {
            return current.time();
        }

        if (current.algoPower() + 1 <= targetAlgoPower) {
            powers.add(new Power(current.algoPower() + 1, current.codingPower(), current.time() + 1));
        }
        if (current.codingPower() + 1 <= targetCodingPower) {
            powers.add(new Power(current.algoPower(), current.codingPower() + 1, current.time() + 1));
        }

        for (int[] problem : problems) {
            int requiredAlgoPower = problem[0];
            int requiredCodingPower = problem[1];
            int rewardAlgoPower = problem[2];
            int rewardCodingPower = problem[3];
            int time = problem[4];

            if (current.algoPower() < requiredAlgoPower || current.codingPower() < requiredCodingPower) {
                continue;
            }

            powers.add(new Power(current.algoPower() + rewardAlgoPower,
                    current.codingPower() + rewardCodingPower,
                    current.time() + time));
        }
    }
    return -1;
}
```

## Review
처음 봤을 떄는 이해가 잘 되지 않아 카카오 테크 블로그의 다익스트라 힌트를 얻어서 풀이할 수 있었다  
근데 visited 의 크기를 정할 때 어느 정도의 크기로 할 지몰라 500으로 했는데 어느 정도의 크기로 해야할까?  
바로 전에 풀었던 문제보다는 쉬운 문제
