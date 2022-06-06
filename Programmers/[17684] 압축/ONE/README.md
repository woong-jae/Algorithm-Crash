# [17684] 압축

## Algorithm
- **Map**

## Logic
- 문자열을 첫번째 문자부터 검사하며 한문자씩 붙인게 맵에 있는지를 검사
  - 만약 맵에 있다면 해당 색인번호를 임시로 저장하고 i의 위치를 붙인 문자의 위치까지로 옮긴다
  - 만약 맵에 없다면 맵에 새로 만든 문자열을 넣고 반복문을 끝내고 임시로 저장했던 색인번호를 리스트에 넣는다

```java
for (int i = 0; i < len; i++) {
    int index = 0;
    StringBuilder builder = new StringBuilder();

    for (int j = i; j < len; j++) {
        builder.append(msg.charAt(j));

        if (map.containsKey(builder.toString())){
            index = map.get(builder.toString());
            i = j;
        }
        else {
            map.put(builder.toString(), indexNum++);
            break;
        }
    }
    answer.add(index);
}
```

## Review
생각보다 쉽게 풀렸던 문제  
근데 조금더 시간을 최적화할 방법이 있을까?
