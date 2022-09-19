# [68645] 삼각 달팽이
## Algorithm
- **구현**

## Logic
- 삼각형을 직각 삼각형으로 생각하여 3가지 경우로 나누어 숫자를 2차원 배열에 표시한다
  - 밑으로 가는 경우
  - 오른쪽으로 가는 경우
  - 왼쪽 위 대각선으로 가는 경우
- 위의 3가지 경우를 반복하며 수를 채우고 2차원 배열에서 0이 아닌 수만 배열에 삽입

```java
private void up() {
    for (int i = currentX, j = currentY; i >= upBound; i--, j--) {
        triangle[i][j] = num++;
        currentY = j;
    }
    downBound--;
    currentX = upBound + 1;
    status = 1;
}

private void down() {
    for (int i = currentX; i <= downBound; i++)
        triangle[i][currentY] = num++;
    rightBound -= 2;
    currentX = downBound;
    currentY++;
    status = 2;
}

private void right() {
    for (int i = currentY; i <= rightBound; i++)
        triangle[currentX][i] = num++;
    upBound += 2;
    currentX--;
    currentY = rightBound - 1;
    status = 0;
}
```

## Review
생각보다 귀찮았던 문제