# [64065] 튜플
## Algorithm
- **String**
## Logic
- 튜플을 분리하여 문자열 길이에 대해 배열을 오름차순 정렬한다
- 분리한 각 튜플을 다시 `,`를 기준으로 숫자들로 분리하여
- Arraylist 의 `contains()` 메소드를 이용해 list 에 숫자가 없다면 넣는다

```java
String[] tokens = s.replace('{',' ').replace('}', ' ').trim().split(" , ");
        Arrays.sort(tokens, Comparator.comparingInt(String::length));

        ArrayList<Integer> list = new ArrayList<>();

        for (String token : tokens)
            for (String num : token.split(","))
                if(!list.contains(Integer.parseInt(num)))
                    list.add(Integer.parseInt(num));
```

## Review
문제는 쉽게 이해해서 코드를 구현했는데 숫자의 중복을 확인하는 과정에서  
이렇게 구현하면 시간이 오래 걸릴거 같은데~ 생각하고 구현을 하긴 했는데  
예상대로 오래 걸렸고 다른 사람의 풀이를 보고 `HashMap` 을 이용해 이거다! 싶어서 
다시 구현하여 검사해봤는데 걸린 시간이 비슷해서 띠용이었다  
그래도 숫자가 많이 커지면 내 방식보다는 `Map` 이나 `Set` 을 이용해 푸는 방법이 더 효율적일 것 같다
