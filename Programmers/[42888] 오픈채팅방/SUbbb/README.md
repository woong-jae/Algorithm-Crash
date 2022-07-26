# [42899] 오픈채팅방

## Algorithm
- Map

## Logic

```java
Map<String, String> user = new LinkedHashMap<>();
ArrayList<Pair> inOut = new ArrayList<>();

for (String rec : record) {
    String[] info = rec.split(" ");
    char word = rec.charAt(0);
    
    if (word == 'E') {
        user.put(info[1], info[2]);
        inOut.add(new Pair(word, info[1]));
    } else if (word == 'L') {
        inOut.add(new Pair(word, info[1]));
    } else {
        user.put(info[1], info[2]);
    }
}
```

- `Map` 에는 유저아이디, 닉네임 쌍을 저장한다.
- `ArrayList<Pair>` 에는 사용자의 입, 퇴장 여부와 해당하는 유저아이디를 저장한다.
- 입, 퇴장이 모두 끝난 후, 순서와 유저아이디에 따라 닉네임을 출력한다.

## Review
- 쉬운 문제였다!