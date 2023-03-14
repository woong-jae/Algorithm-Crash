# [150369] 택배 배달과 수거하기
## Algorithm
- **Greedy**

## Logic
- **가장 먼 집**부터 수거 및 회수를 한다고 생각한다
- 이 때, 수거 또는 회수를 할 수 있는 집(둘 중 하나라도 0 이상)이 있을 때 몇 번을 방문해야(count * number) 수거 또는 회수를 완료할 수 있는지를 확인한다
```java
int count = 0;
while (deliver < deliveries[index] || pickup < pickups[index]) {
    ++count;
    deliver += cap;
    pickup += cap;
}
deliver -= deliveries[index];
pickup -= pickups[index];
```
- 해당 집을 왕복한 거리이기 때문에 집의 번호 * 왕복 횟수 * 2를 해서 총 합 거리에 더해준다
```java
sumOfDistance += (index + 1) * 2L * count;
```

## Review
막상 시험 때도 비슷하게 풀었던 것 같다  
찾아보니 *Stack*을 활용한 방법도 있던데 위의 방법이 더 효율적일 것 같다! 