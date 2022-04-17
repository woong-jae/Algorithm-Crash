# [64061] 크레인 인형뽑기 게임
## Algorithm
- **Stack**
## Logic
문제 접근 방법(시간 복잡도 꼭 작성)
- 숫자를 담고 없애는 역할을 하는 바구니를 `Stack` 을 이용해 구현
- 크레인을 이용해 해당 Col 에 있는 가장 위에 있는 수를 뽑는 `private method` 로 구현
  - 해당 col 이 비어있다면 0 을 반환
  - 해당 col 이 비어있지 않다면 해당 수를 가져오고 수가 있던자리를 0 으로 변경  
```java
private int topNum(int[][] board, int col) {
    for (int[] row : board)
        if (row[col - 1] != 0){
            int topNum = row[col - 1];
            row[col - 1] = 0;
            return topNum;
        }
    return 0;
}
```
- 크레인에서 뽑아온 수와 Stack 의 제일 위에 있는수를 pop 해서 비교  
- 같다면 뽑아온 수를 Stack 에 넣지않고 pop 한 숫자와 같이 count 
- 다르면 Pop 한 수를 다시 Stack 에 넣고 뽑아온 수도 스택에 넣어줌
```java
for (int move : moves) {
    int top = topNum(board, move);
    if (top != 0) {
        // 만약 바구니가 비어있으면 top 넣고 continue
        if (basket.isEmpty()) {
            basket.push(top);
            continue;
        }

        int basketTop = basket.pop();

        if(top == basketTop)
            answer += 2;

        else {
            basket.push(basketTop);
            basket.push(top);
        }
    }
}
```
- 시간복잡도 : move 의 반복문과 col 의 제일 위에 있는 수를 찾기위한 반복문으로 O(N^2)이다

## Review
문제에 대한 아이디어 자체는 쉽게 떠올릴수 있었던 것 같다  
떠올릴수 있는 방법에서는 최선의 방법이었던것 같다!
