# [2800] 괄호 제거
## Algorithm
- **Stack**, **Permutation**

## Logic
- 소괄호의 여는 위치와 닫는 위치 쌍의 인덱스를 저장할 Parentheses class 생성
- Stack을 사용해서 여는 소괄호와 닫는 소괄호를 쌍으로 만들어 List에 저장 
  - 이때 소괄호가 아닌 일반 숫자와 수식의 위치를 check boolean 배열에 저장
- 순열로 괄호쌍을 뽑는 경우와 안뽑는 경우로 나누어 경우의 수를 만들고 만들어진 경우의 수를 가지고 문자열을 생성
  - 만들어진 문자열 중 모두 선택되는 경우는 원본과 같으므로 set에서 삭 

```java
private static void find(String expression) {
    Stack<Parentheses> stack = new Stack<>();
    for (int i = 0; i < expression.length(); i++) {
        // 여는 소괄호 일 때 스택에 삽입
        if (expression.charAt(i) == '(')
            stack.add(new Parentheses(i));
        // 닫는 소괄호 일 때 스택에서 꺼내어 닫는 위치를 저장하고 괄호쌍 리스트에 삽입
        else if (expression.charAt(i) == ')') {
            Parentheses parentheses = stack.pop();
            parentheses.close = i;
            list.add(parentheses);
        }
        // 일반 숫자 또는 수식은 표시만 저장
        else
            check[i] = true;
    }
}

private static void dfs(int depth, String expression, boolean[] visited) {
    if (depth == list.size()) {
        // 일반 숫자와 수식을 저장하고 있는 check 배열을 복사
        boolean[] copied = Arrays.copyOf(check, check.length);

        // 고른 괄호쌍들의 위치를 true 로 표시
        for (int i = 0; i < list.size(); i++)
            if (visited[i]) {
                copied[list.get(i).open] = true;
                copied[list.get(i).close] = true;
            }

        // 표시된 배열을 바탕으로 문자열을 만들고 set에 삽입
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < expression.length(); i++)
            if (copied[i])
                sb.append(expression.charAt(i));
        result.add(sb.toString());
        return;
    }

    dfs(depth + 1, expression, visited);
    visited[depth] = true;
    dfs(depth + 1, expression, visited);
    visited[depth] = false;
}
```

## Review
크게 어렵지 않은 문제