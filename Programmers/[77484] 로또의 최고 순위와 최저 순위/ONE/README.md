# [77484] 로또의 최고 순위와 최저 순위
## Algorithm
- **구현**

## Logic
- 일치하는 수의 개수 : containCount
- 0의 개수 : zeroCount
- 최대 등수는 0의 개수와 일치하는 개수를 더한 개수에 해당하는 등수
- 최소 등수는 일치하는 개수에 해당하는 등수

```java
public int[] solution(int[] lottos, int[] win_nums) {
    int[] answer = new int[2];
    int containCount = 0;
    int zeroCount = 0;
    Map<Integer, Integer> map = new HashMap<>();
    Set<Integer> win_num = new HashSet<>();

    for (int num : win_nums)
        win_num.add(num);

    for(int i = 1; i <= 6; i++)
        map.put(7 - i, i);
    map.put(0, 6);

    for (int num : lottos) {
        if (num == 0) {
            zeroCount++;
            continue;
        }
        if(win_num.contains(num))
            containCount++;
    }
    answer[0] = map.get(containCount + zeroCount);
    answer[1] = map.get(containCount);

    return answer;
}
```

## Review
쉬운문제지만 이 문제가 이번주의 유일한 숨통
