# [17676] 추석 트래픽

## Algorithm
- 문자열

## Logic

```java
private void getTime(String[] doneTime, int procTime) {
    int hour = Integer.parseInt(doneTime[0]);
    int min = Integer.parseInt(doneTime[1]);
    int sec = (int) (Double.parseDouble(doneTime[2]) * 1000);

    int endSec = hour * 1000 * 60 * 60 + min * 1000 * 60 + sec;
    int startSec = endSec - procTime + 1;

    times.add(new Time(startSec, endSec));
}
```
- 문자열을 파싱하여 시, 분, 초 단위로 변환 후 이를 정수로 변환

```java
private int findMaxRequest(int startTime) {
    int count = 0;
    // start ~ end 사이에 있다면 count + 1;
    int endTime = startTime + 1000;

    for (Time time : times) {
        if (startTime <= time.startSec && time.startSec < endTime) {
            count++;
        } else if (startTime <= time.endSec && time.endSec < endTime) {
            count++;
        } else if (endTime <= time.endSec && time.startSec <= startTime) {
            count++;
        }
    }

    return count;
}
```
- 주어진 시간은 로그의 시작 시간 또는 종료 시간
  - 해당 시간으로부터 1초 내에 다른 로그가 존재한다면 `count` 를 증가

## :black_nib: **Review**

- 문자열 파싱하는 과정부터 마음에 들지 않은 문제였고, 애매한 설명때문에 이해가 더 가지 않았다.
- 처음에는 초 단위의 정보만을 가지고 문제를 해결하려다가 아차 싶었다.
- 데이터 형 변환 또한 좀 더 꼼꼼히 확인하는 습관을 가져야겠다.