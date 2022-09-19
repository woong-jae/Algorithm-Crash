# [12987] 숫자 게임
## Algorithm
- **구현**

## Logic
- A와 B를 오름차순 정렬하여 뒤에서 부터 검사하며 B의 원소가 A의 원소보다 큰 경우 승리 카운트를 한후 B의 인덱스를 1 줄인다
  - 이렇게 하면 A를 이기는 경우에 B가 가장 작은 원소가 된다

```java
Arrays.sort(A);
Arrays.sort(B);

for (int indexA = N - 1, indexB = N - 1; indexA >= 0; indexA--)
    if (B[indexB] > A[indexA]){
        answer++;
        indexB--;
    }
```

## Review
생각보다 어렵지 않은 문제  
벌금 시원하다~