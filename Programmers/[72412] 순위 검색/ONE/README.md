# [72412] 순위 검색
## Algorithm
- **Binary Search**

## Logic
- 입력 받은 정보를 " "로 분리하여 원래 정보를 넣거나 '-'를 넣는 경우의 수로 문자열을 만들어 Map에 넣는다
- 이진 탐색을 위해 맵의 Value 들을 정렬해준다
- query로 받은 문자열을 분리해 key 형태의 문자열로 만들어 map에 있는지 확인하고,  
- 만약에 있다면 이진 탐색을 통해 스코어보다 크거나 같은 지점을 찾아 개수를 구하고
- 없다면 0을 답에 넣어준다

```java
public int[] solution(String[] info, String[] query) {
    int n = query.length;
    int[] answer = new int[n];

    for (String information : info)
        makeStringCases(0, new boolean[4], makeList(information));

    for (List<Integer> list : map.values())
        Collections.sort(list);

    for (int i = 0; i < n; i++) {
        query[i] = query[i].replaceAll(" and ", "");
        String[] tokens = query[i].split(" ");
        String key = tokens[0];
        int score = Integer.parseInt(tokens[1]);

        answer[i] = map.containsKey(key) ? binarySearch(map.get(key), score) : 0;
    }
    return answer;
}
```

## Review
map을 이용해서 하는건 금방 생각 났지만 점수를 이진탐색으로 구하는 것에 오래 붙잡혀 있던 것 같다  
이진 탐색을 자주 써서 손에 익혀야 할 듯!