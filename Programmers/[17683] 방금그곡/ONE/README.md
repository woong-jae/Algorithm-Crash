# [17683] 방금그곡

## Algorithm
- **Priority Queue**

## Logic
- 음악에 대한 정보를 생성자로 전달 받아 각각 시간 멜로디 제목으로 파싱한다
  - 이 때, 멜로디에 `#`이 들어간 음들은 소문자로 치환해서 나중에 `contains()` 메소드를 사용할 때 방해가 되지않도록 해준다
  - 만약에 치환해주지 않는다면 끝부분에 `C#`으로 끝나는 멜로디인데 `C`로 끝나는 멜로디로 오해되는 것과 같은 상황이 발생할 수 도 있다
- 각 음악마다 시간길이로 만들어진 멜로디가 조건에 해당되는 멜로디를 가지고 있으면 시간길이에 따라 내림차순 정렬되는 우선순위 큐에 넣어준다
- 큐가 비어있다면 `"(None)"`을 반환
- 비어있지 않다면 큐의 제일앞의 음악의 제목을 반환

```java
Queue<Music> queue = new PriorityQueue<>();

for (String musicInfo : musicinfos) {
    Music music = new Music(musicInfo);

    m = m.replace("C#", "c").replace("D#", "d")
            .replace("F#", "f").replace("G#", "g")
            .replace("A#", "a");

    if(music.melody.contains(m))
        queue.add(music);
}

if(!queue.isEmpty())
    return queue.poll().title;
else
    return "(None)";
```

## Review
아무생각 없이 # 조건을 생각안하고 풀었었는데  
테스트 케이스에 # 조건을 생각해야 하는게 있어서 금방 해결 할 수 있었다
