# [12929] 올바른 괄호의 갯수
## Algorithm
- **Stack**

## Logic
- 중복을 제외한 순열의 경우의 수를 구해서  
- Stack을 이용해 해당 괄호가 올바른지를 판단
  - 닫는 괄호일 때, 스택이 비엇거나 스택의 최상단이 여는괄호가 아니면 올바르지 않은 경우

```java
private void permutation(int n, int cntOpen, int cntClose, List<Integer> result) {
    if (cntOpen == 0 && cntClose == 0) {
        if (isRight(result))
            answer++;
        return;
    }
    if (cntOpen > 0) {
        result.add(1);
        permutation(n, cntOpen - 1, cntClose, result);
        result.remove(2 * n - cntClose - cntOpen);
    }
    if (cntClose > 0) {
        result.add(2);
        permutation(n, cntOpen, cntClose - 1, result);
        result.remove(2 * n - cntClose - cntOpen);
    }
}
```
## Review
레벨 4 치고는 쉬웠던 듯!