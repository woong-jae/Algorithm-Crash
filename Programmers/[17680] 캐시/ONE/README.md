# [17680] 캐시
## Algorithm
- **Queue**

## Logic
- cache 크기가 0 일 때는 무조건 miss 이므로 도시 수만큼 *5 해서 return
- 도시 이름을 전부 소문자로 바꿔주고 만약 `Queue`에 같은 이름의 도시가 있다면 (`hit` 일 때)
- 큐에서 해당 도시를 빼고 다시 큐에 넣어주는 최신화 작업을 해준 뒤 hit 이므로 +1 한다
- 만약 `Queue`에 해당 도시의 이름이 없을 때(`miss` 일 때)
  - 현재 캐시 크기보다 같거나 크다면 큐에서 가장 오래된 제일 앞의 도시를 빼주고 새로운 도시를 더하고 +5 한다
  - 현재 캐시 크기보다 작으면 그냥 도시를 더하고 +5 해준다

```java
public int solution(int cacheSize, String[] cities) {
    int answer = 0;
    Queue<String> queue = new LinkedList<>();

    if (cacheSize == 0)
        return cities.length * 5;

    for (String city : cities) {
        city = city.toLowerCase();

        if(queue.contains(city)){
            queue.remove(city);
            queue.add(city);
            answer += 1;
        }
        else {
            if(queue.size() >= cacheSize)
                queue.poll();
            queue.add(city);
            answer += 5;
        }
    }
    return answer;
}
```

## Review
다음주가 걱정되는 쉬운문제
