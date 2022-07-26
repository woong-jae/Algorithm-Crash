# [42888] 오픈채팅방
## Algorithm
- **Map**

## Logic
- 채팅방에 출력한 메시지의 종류는 입장 또는 퇴장 밖에 없기 때문에 boolean 과 uid를 저장하는 Log class 사용
- uid값을 key, 이름을 vlaue로 갖는 Map 사용
- 이름을 변경하는 것은 map.put()만 해준다
- 입장했을 때에는 이름을 map.put()해주고 log의 boolean 값을 true로 하여 생성하고 queue에 삽입
- 퇴장했을 때에는 queue에 log의 boolean 값을 false로 해서 넣어준다
- queue에서 하나씩 꺼내고 uid에 해당되는 이름을 출력해주면 된다

```java
for (String r : record) {
    StringTokenizer st = new StringTokenizer(r);
    String request = st.nextToken();
    String uid = st.nextToken();
    String name;

    switch (request) {
        case "Enter":
            name = st.nextToken();
            user.put(uid, name);
            logs.add(new Log(true, uid));
            break;
        case "Leave":
            logs.add(new Log(false, uid));
            break;
        case "Change":
            name = st.nextToken();
            user.put(uid, name);
    }
}
```

## Review
Map만 쓰면되는 쉬운문제
