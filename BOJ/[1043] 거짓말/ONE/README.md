# [1043] 거짓말
## Algorithm
- **구현**

## Logic
- M번 반복하여 파티에서 진실을 아는 사람들을 계속 갱신해준다 
  - M번을 반복하는 이유 -> 파티의 순서는 중요하지 않고 나중에 알게된 사람이 이전 파티에도 참여했을 수 있기 때문

```java
for (int i = 0; i < M; i++)
    for (int j = 0; j < M; j++)
        if (parties[j].stream().anyMatch(index -> people[index]))
            parties[j].forEach(index -> people[index] = true);

System.out.println(Arrays.stream(parties)
        .filter(party -> canLie(people, party))
        .count());
```

## Review
처음에는 1번 반복하여 진실을 아는 사람을 갱신했는데,  
반례가 생각나 M번 반복으로 수정하여 해결할 수 있었다  
생각보다 쉬운문제