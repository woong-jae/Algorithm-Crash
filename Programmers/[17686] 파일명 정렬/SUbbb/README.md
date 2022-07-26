# [17686] 파일명 정렬

## Algorithm
- 문자열
- 정규 표현식

## Logic

```java
fileList.sort(((o1, o2) -> {
    // head가 동일하다면, number 기준 오름차순 정렬
    if (o1.head.compareTo(o2.head) == 0) return o1.number - o2.number;
    // head 기준 사전순 정렬
    return o1.head.compareTo(o2.head);
}));
```
- `head` 가 동일한 경우와 동일하지 않은 경우에 대한 정렬 조건 명시

## Review
- `head` 와 `number` 에 대한 정렬 조건만 세운다면 해결되는 문제!