# [67256] 키패드 누르기 - Java

## :pushpin: **Algorithm**

수학

## :round_pushpin: **Logic**

```java
int leftDist = Math.abs(number.x - left.x) + Math.abs(number.y - left.y);
int rightDist = Math.abs(number.x - right.x) + Math.abs(number.y - right.y);
```

- 각 엄지손가락과 누를 번호의 거리를 계산한다.

## :black_nib: **Review**

- 처음에는 무작정으로 구현했으나, 번호 간 거리를 구하는 깔끔한 방법이 있을 것 같아서 고민하다가 피타고라스에서 세 점 간 거리 구하듯 거리를 구하면 될 것 같아 구현해보았다.
- 처음에는 2차원 배열에 각 키패드 번호들을 저장해두고 좌표를 사용할까 했는데, 주어진 번호로부터 좌표를 바로 얻어낼 수 있을 것 같아 배열을 지웠다.