# [64064] 불량 사용자

## Algorithm
- 문자열, DFS

## Logic

```java
public void findBadUser(HashSet<String> set, int size) {
    if (size == bannedIdList.size()) {
        banSet.add(new HashSet<>(set));
        return;
    }

    // list번째 제재 아이디를 set에 추가
    for (String id : bannedIdList.get(size)) {
        if (set.contains(id)) continue;
        set.add(id);
        findBadUser(set, size + 1);
        set.remove(id);
    }
}
```
- DFS 방식으로 제재 아이디 `set` 을 생성하고, 이를 중복이 없도록 하기 위해 `HashSet` 에 저장한다.

## Review
- 정규 표현식만으로 해결하려고 했지만, DFS 방식으로 중복을 확인하는 과정이 필요했다.
- 이런 유형의 DFS, BFS 방식의 문제도 있을 수 있다는 것을 알게 되었다.