# [64065] 튜플

## Algorithm
- 문자열

## Logic

**이전 버전**
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

**새로운 버전**
```java
Pattern pattern = Pattern.compile("\\d+");
Matcher matcher = pattern.matcher(s);

while(matcher.find()) {
    int num = Integer.parseInt(matcher.group(0));
    map.put(num, map.getOrDefault(num, 0) + 1);
}
```
- 정규 표현식을 사용해 숫자만 추출한다.
- 이후 각 숫자의 등장 횟수를 `Map` 에 저장하고, 등장 횟수 기준으로 정렬한다.

## Review
- 아이디어는 쉽게 잡았지만 이를 구현해내는 방법이 효율적이지 않은 듯 하다.
- 어떻게 하면 더 효율적이게 바꿀 수 있을까를 고민하다, 숫자별 등장 횟수에 포인트를 두고 얼마 전 정리한 정규 표현식이 떠올라 사용해보았다.
  - 문자열 알고리즘에서 정규 표현식이 많이 사용된다고 했는데, 아주 잘 써먹었다.