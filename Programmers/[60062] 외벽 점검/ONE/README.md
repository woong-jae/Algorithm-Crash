# [60062] 외벽 점검
## Algorithm
- **Permutation**

## Logic
- weak 배열을 한 칸씩 밀어준 배열을 rotateWeak에 저장
- 순열로 친구 거리를 뽑아주고 원하는 인원수(length)가 채워졌다면 얘들로 벽을 점검이 가능한지 검사
- index는 외벽 배열의 index를 가리키고, start는 일을 해야 할 포인트의 값
- while문을 통해 친구들 중 한 명이 일을 할 수 있는 범위까지 밀어줌
- 친구들이 일을 끝냈다면 index가 배열 밖으로 밀려날 것이고 이때 친구들의 인원수의 최솟값으로 최적화

```java
public int solution(int n, int[] weak, int[] dist) {
    answer = Integer.MAX_VALUE;
    this.n = n;
    this.weak = weak;
    this.dist = dist;
    rotateWeak = new int[weak.length][weak.length];
    visit = new boolean[dist.length];

    rotate();

    for (int i = 1; i <= weak.length; i++)
        permutation(0, i, "");

    return answer == Integer.MAX_VALUE ? -1 : answer;
}
```

## Review
처음에는 가장 큰거리의 weak 사이를 하나씩 제거하면서 분리하는 방식으로 구현했는데  
해당 방식은 균일하게 분할 할 수가 없어서 결국 다른 사람의 코드를 참고했다  
완전탐색으로 밖에 풀 수 없어서 아쉬웠던 문제