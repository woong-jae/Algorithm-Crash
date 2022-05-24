# [1083] 소트
## Algorithm
- **Bubble Sort**

## Logic
  - 현재 위치에서 S 만큼의 거리에서 가장 큰수를 제일 앞으로 오게하고  
  - 이동한 수만큼 S에서 빼면서 이를 반복한다

```java
for (int i = 0; i < N; i++) {
    int max = list.get(i);
    int maxIndex = i;

    for (int j = i + 1; j < N && j <= i + S; j++)
        if (max < list.get(j)) {
            max = list.get(j);
            maxIndex = j;
        }

    for (int j = maxIndex; j > i; j--)
        Collections.swap(list, j - 1, j);

    S -= maxIndex - i;

    if (S <= 0)
        break;
}
```

## Review
문제를 이해하는데 시간이 걸렸고 반복문에서 j < i 와 같이 부호를 반대로해서  
바보같이 시간이 더 걸렸다... 크게 어렵지 않은 문제
