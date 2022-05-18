# [17677] 뉴스 클러스터링

## Algorithm
- **String**

## Logic
- 문자열의 대소문자를 구분하지 않으므로 전부 소문자로 바꾸고 알파벳으로만 이루어진 부분 문자열 리스트들을 만든다
- 교집합은 `List`의 크기를 비교하여 크기가 작은 리스트를 반복문 돌면서 길이가 더 긴 리스트와 원소를 비교하여 같으면 교집합에 추가 해 준다
- 합집합은 `A + B - 교집합`의 형식으로 구한다
    - 교집합을 빼 줄 때 `removeAll`을 사용하면 중복 원소도 지워버리기 때문에 하나씩 지워준다 

```java
List<String> intersection;
List<String> union = new ArrayList<>(A);

if(A.size() >= B.size())
    intersection = makeIntersection(A, B);
else
    intersection = makeIntersection(B, A);

union.addAll(B);
for(String i : intersection)
    union.remove(i);
```

## Review
알파벳만으로 이루어진 것을 검사할 때, 처음에 아스키 코드를 썼는데  
8번 케이스만 통과하지 못하여 질문에서 정규표현식을 사용해야 하는 것을 알고 수정하여 성공했다
