# [42890] 후보키
## Algorithm
- **Combination**

## Logic
- 후보키를 저장할 HashSet 생성
- Attribute를 한개부터 1개씩 늘려가면서 유일성과 최소성을 검사
- 경우의 수를 구하는 것은 재귀를 통한 조합으로 구현
- 해당 개수의 열을 뽑아 유일성이 증명되면 최소성을 검사
- 최소성은 Set에서 해당 열들중 포함하고 있는 부분이 있는 지를 검사
  - 예를 들어 0번쨰와 2번쨰가 Set에 02로 들어있을 때,
  - 012는 최소성을 만족하지 못한다


```java
private void checkUniqueness(int i, int depth, boolean[] visited, String[][] relation) {
    if (i == depth) {
        Set<String> testSet = new HashSet<>();

        for (String[] r : relation) {
            StringBuilder builder = new StringBuilder();
            for (int j = 0; j < attributeNum; j++)
                if(visited[j])
                    builder.append(r[j]);
            testSet.add(builder.toString());
        }

        if (testSet.size() == size) {
            StringBuilder builder = new StringBuilder();
            for (int j = 0; j < attributeNum; j++)
                if (visited[j])
                    builder.append(j);
            if(isMinimality(builder.toString()))
                uniqueSet.add(builder.toString());
        }
        return;
    }

    for (int j = 0; j < attributeNum; j++)
        if ( !visited[j]) {
            visited[j] = true;
            checkUniqueness(i, depth + 1, visited, relation);
            visited[j] = false;
        }
}
```

## Review
최소성에서 자꾸 테스트 케이스가 5개가 틀려서 질문을 참고했다  
내가 구현한 대로라면 02, 012 이런식으로 열의 인덱스를 문자열로 Set에 저장했는데  
이렇게 되면 최소성을 만족할 수가 없어서 0과 2를 따로 분리해서 포함여부를 검사하도록 구현해서 통과했다  
쬐끔 생각이 더 필요했던 문제...
