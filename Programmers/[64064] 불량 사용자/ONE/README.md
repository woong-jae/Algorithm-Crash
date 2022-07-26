# [64064] 불량 사용자
## Algorithm
- **DFS**

## Logic
- 정규표현식 `matches()` 를 사용할 수 있도록 `*` 들을 `_`로 바꾼다 
- `DFS`로 `user_id`와 일치할 모든 경우의 수로 문자열을 만들어 `Set`에 저장하고 크기를 반환

```java
private void solve(Set<String> set, int idx, boolean[] visit, String[] user_id) {
    if (idx == banned.length) {
        StringBuilder builder = new StringBuilder();

        for(int i = 0; i < user_id.length; i++)
            if(visit[i])
                builder.append(user_id[i]).append("-");

        set.add(builder.toString());

        return;
    }

    for (int i = 0; i < user_id.length; i++)
        if(!visit[i] && user_id[i].matches(banned[idx])) {
            visit[i] = true;
            solve(set, idx + 1, visit, user_id);
            visit[i] = false;
        }
}
```

## Review
어디서 이것과 비슷한 문제를 풀어본적이 있어서 푸는 방법을 알 수 있었다  
`matches()`의 소중함을 느낄 수 있는 문제
