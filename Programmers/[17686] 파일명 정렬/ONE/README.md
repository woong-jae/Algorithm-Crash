# [17686] 파일명 정렬
## Algorithm
- **Sorting**

## Logic
- `Pattern, Matcher`를 이용해서 `문자 / 숫자`로 구분하여 HEAD, NUMBER, 원본 문자열을 저장하는 class 생성
- 해당 class로 리스트를 만들고 HEAD 기준으로 사전순 정렬을 한다
  - HEAD가 같다면 NUMBER을 기준으로 오름차순 정렬하고 
  - NUMBER 까지 같다면 순서를 유지한다
- 저장된 리스트 순서의 class 들에서 원본 문자열들로 배열을 만들어 반환

```java
public String[] solution(String[] files) {
    ArrayList<File> list = new ArrayList<>();
    ArrayList<String> answer = new ArrayList<>();

    for (String file : files)
        list.add(new File(file));

    list.sort((o1, o2) -> {
        if (o1.head.compareTo(o2.head) == 0)
            return o1.number - o2.number;
        return o1.head.compareTo(o2.head);
    });

    for(File file : list)
        answer.add(file.origin);

    return answer.toArray(new String[0]);
}
```

## Review
그냥 파싱이랑 소팅만하면 되는 문제