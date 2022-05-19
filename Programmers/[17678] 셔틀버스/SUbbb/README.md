# [17678] 셔틀버스

## Algorithm
- 문자열
- 우선순위 큐

## Logic

```java
public int rideBus() {
    int departBus = 540;

    for (int i = 0; i < n; i++) {
        while(!crueTimes.isEmpty()) {
            int crue = crueTimes.peek();

            if (departBus >= crue && busTimes.get(i).size() < m) {
                crueTimes.poll();
                busTimes.get(i).add(crue);
                lastCrue = crue - 1;
            } else break;
        }
        departBus += t;
    }
    
    if (busTimes.get(n - 1).size() < m) 
        lastCrue = departBus - t;
    
    return lastCrue;
}
```
- `departBus` 로 버스 출발 시각을 세팅하고, 우선순위 큐에 저장된 크루들의 대기열 도착 시간을 하나씩 비교하며 탑승할 수 있는 경우 큐에서 제외한다.
- 이때 마지막으로 탑승한 인원의 대기열 도착 시간을 알아야 하기에 `lastCrue` 에 저장한다. (이때 -1한 값을 저장)
- 콘은 마지막 버스에만 타면 되므로, 마지막 버스가 만석인지 확인하여
  - 만석인 경우, `lastCrue` 를 반환하고,
  - 만석이 아닌 경우, 해당 버스의 출발 시각을 반환한다.

## :black_nib: **Review**
- 버스마다 탈 수 있는 인원을 모두 채워놓고, 맨 마지막 버스에 대해서만 확인하여 제일 늦은 시각을 구하려고 했는데, 테케 14 ~ 16번이 죽어도 실패였다.
- 생각나는 테케는 다 만들어서 해봐도 도저히 뭐가 안 맞는 건지 모르겠어서 다른 풀이를 봤다.
- 누가 좀 속시원하게 알려줬으면 좋겠다 ...