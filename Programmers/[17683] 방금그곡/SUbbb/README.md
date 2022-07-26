# [17683] 방금그곡

## Algorithm
- 문자열
- Map

## Logic

```java
// #이 들어가는 음 치환
private String convert(String s) {
    return s.replace("C#", "c").replace("D#", "d").replace("F#", "f").replace("G#", "g").replace("A#", "a");
}
```
- `#` 이 들어가는 경우 `contains()` 로 포함 여부 확인 시 추가 조건이 필요해서 치환하는 방식을 사용했다.

```java
String answer = "";
for (Map.Entry<String, String> entry : music.entrySet()) {
    String score = entry.getValue();
    int playTime = score.length();
    
    // 기억한 멜로디를 포함하고, 재생 시간이 제일 긴 음악인 경우 answer에 해당 제목 저장
    if (score.contains(m) && playTime > maxPlayTime) {
        answer = entry.getKey();
        maxPlayTime = playTime;
    }
}
```
- `LinkedHashMap` 에 들어있는 악보를 기억하고 있는 멜로디와 비교하면서,
  - 해당 멜로드를 포함하고,
  - 재생 시간이 제일 긴 음악인지를 판단하여 노래 제목을 저장한다.

## Review
- 재생 시간 조건을 고려했어야 하는 문제였고, 구현해나가던 중 계속 발생하는 실패때문에 질문 게시판을 확인했는데, `Map` 을 `for` 문으로 탐색하게 되면 순서가 보장되지 않는다는 점을 깨달았다. `LinkedHashMap` 을 정리해놓고 적용해보지 못한 사례였다.