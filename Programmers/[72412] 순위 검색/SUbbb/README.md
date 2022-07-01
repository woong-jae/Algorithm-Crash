# [72412] 순위 검색

## Algorithm
- DFS

## Logic

```java
// -를 붙이면서 가능한 조합을 생성하여 map에 추가한다.
private static void makeComb(int length) {
    if (length == 4) {
        String key = String.join("", combStr);
        scoreMap.putIfAbsent(key, new ArrayList<>());
        scoreMap.get(key).add(infoScore);
    } else {
        // -가 아닌 경우와 -인 경우로 나눠 가능한 조합 생성
        combStr[length] = infoLine[length];
        makeComb(length + 1);
        combStr[length] = "-";
        makeComb(length + 1);
    }
}
```
- -를 붙이면서 가능한 정보를 만드는 함수
- DFS 방식으로 기존의 정보를 사용하거나, -를 붙이거나 하여 생성한다.

## Review
- -가 포함된 경우를 위해 가능한 모든 정보를 만들어두고 이를 사용하는 아이디어를 사용했다.
- 이진 탐색을 사용해 빠르게 인원을 구하는 아이디어가 주요했던 것 같다.