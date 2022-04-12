# [64065] 튜플

## Algorithm
- 문자열

## Logic

```java
while(!list.isEmpty()) {
    int[] tmp = Arrays.stream(list.get(0).replace(" ","").split(",")).mapToInt(Integer::parseInt).toArray();
    
    for (int i : tmp)
        if (!answer.contains(i)) answer.add(i);
    
    list.remove(0);
}
```
- 각 요소별 개수 기준으로 정렬하고, 첫 번째 요소의 값을 튜플의 첫 요소로 삽입한다.
- 이후 정렬된 배열을 순차적으로 돌면서, 이미 튜플에 넣은 값이 아닌 값이 나오면 튜플에 삽입하고, 이를 반복하며 튜플을 완성한다.

## Review
- 아이디어는 쉽게 잡았지만 이를 구현해내는 방법이 효율적이지 않은 듯 하다.