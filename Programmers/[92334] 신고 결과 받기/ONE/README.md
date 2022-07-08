# [92334] 신고 결과 받기
## Algorithm
- **Map**, **Set**

## Logic
- 신고당한 유저를 key, 신고를 한 사람들을 중복없이 저장할 Set을 value로 갖는 Map 생성
- 유저와 신고 메일을 받은 횟수를 저장할 Map 생성
- 만약 set의 사이즈가 k 이상이라면 countMap에 유저와 신고당한 횟수 +1을 해준다

```java
int n = id_list.length;
int[] answer = new int[n];
Map<String, Set<String>> reportedMap = new HashMap<>();
Map<String, Integer> countMap = new HashMap<>();

for (String s : report) {
    StringTokenizer st = new StringTokenizer(s);
    String user = st.nextToken();
    String reported = st.nextToken();

    if(!reportedMap.containsKey(reported))
        reportedMap.put(reported, new HashSet<>());
    reportedMap.get(reported).add(user);
}

for (String s : reportedMap.keySet())
    if (reportedMap.get(s).size() >= k)
        for (String user : reportedMap.get(s))
            countMap.put(user, countMap.getOrDefault(user, 0) + 1);

for (int i = 0; i < n; i++) {
    if(!countMap.containsKey(id_list[i]))
        continue;
    answer[i] = countMap.get(id_list[i]);
}
```

## Review
쉬운문제
