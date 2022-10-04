# [1107] 리모컨
## Algorithm
- **DFS**

## Logic
- 100번 채널에서 0~500000 채널중 하나로 이동하는 최소 이동 수 구하기
  - 숫자 버튼 중 몇개가 고장나 있어 누르지 못한다
- 최대 자릿수가 6자리이기 때문에, 고장나지 않은 버튼 중에서 버튼을 눌러서 갈 수 있는 모든 경우의 수 중 최소 이동 횟수를 구한다
  - 기본 이동 수를 +, -로만 이동한 이동 수로 초기화 한다
  - 목적지 채널의 자릿수 n과 n±1 일 때의 경우의 수 중 이동 수가 최소인 경우를 구한다

```java
private static int move(int n) {
    if (destination == 100)
        return 0;

    answer = Math.abs(destination - 100); // 기본값을 +, - 로만 이동하는 횟수로 설정

    dfs(0, n - 1, new StringBuilder()); // EX) 10000 이고 0, 1 버튼이 고장 났을 때 9999가 제일 최소 횟수
    dfs(0, n, new StringBuilder());
    dfs(0, n + 1, new StringBuilder()); // EX) 9999 이고 9 버튼이 고장 났을 때 10000이 제일 최소 횟수

    return answer;
}

private static void dfs(int depth, int n, StringBuilder sb) {
    if (depth >= n) {
        if (!sb.toString().equals(""))
            answer = Math.min(answer, n + Math.abs(destination - Integer.parseInt(sb.toString())));
        return;
    }

    for (int i = 0; i < 10; i++)
        if (!buttons[i]) {
            dfs(depth + 1, n, sb.append(i));
            sb.delete(sb.length() - 1, sb.length());
        }
}
```

## Review
첫문제 부터 살 것같다...  
근데 무언가 더 좋은 방법이 있을 것만 같기도 한 문제