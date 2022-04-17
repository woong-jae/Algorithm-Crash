# [64061] 크레인 인형뽑기 게임

## Algorithm
- Stack

## Logic

```java
int doll = board[r][move - 1];
if (basket.size() >= 1) {
    if (basket.peek() == doll) { 
        basket.pop();
        answer += 2;
    }
    else basket.push(doll);
} else basket.push(doll);
```
- 주어진 `move` 열의 제일 위에 있는 인형을 뽑고 스택을 사용한 바구니의 최상단 값과 비교하여 바구니에 넣을지, 제거할지 결정한다.

## Review
- 스택을 사용한 간단한 문제였다.
- 중복되는 `else` 문을 제거할 수 있지 않을까.