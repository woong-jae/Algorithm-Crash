# [1000] A+B

## Algorithm

- regular expression

## Logic

1. `musicinfos`를 순회하며 아래와 같이 파싱한다.

```js
const [start, end, title, music] = musicinfo.split(',');
```

2. 주어진 `m`과 `music`의 #으로 끝나는 음을 # 앞의 문자의 소문자로 대체한다.

- 즉, #이 없도록 한다.

3. `start`와 `end`를 파싱한 후 분 단위로 변환하여 차이(재생 시간)를 구한다.

4. `m`과 `music`이 같은 음악인지 확인한다.

```js
function checkSameMusic(melody, music, playTime) {
  if (playTime < music.length) {
    music = music.slice(0, playTime);
  } else {
    while (playTime > music.length) {
      music += music;
    }
  }

  return music.includes(melody);
}
```

- 재생 시간보다 `music`의 길이가 클 경우, 재생 시간만큼 `music`을 자른다.

- 재생 시간보다 `music`의 길이가 작을 경우, 재생 시간보다 커질 때까지 `music`을 이어 붙인다.

- 위 과정을 거친 후 `m`(`melody`)이 `music`에 속하는지 확인한다.

### 시간복잡도: O(N^2)

## Review

"각 음은 1분에 1개씩 재생된다. 음악은 반드시 처음부터 재생되며 음악 길이보다 재생된 시간이 길 때는 음악이 끊김 없이 처음부터 반복해서 재생된다. 음악 길이보다 재생된 시간이 짧을 때는 처음부터 재생 시간만큼만 재생된다."

위 문장을 제대로 이해하지 못했다. 

그래서 자꾸 30번 테스트 케이스를 틀렸다.

히든 케이스를 고민하다 문제를 다시 차근차근 읽은 후 다시 이해하고, 재생 시간보다 `music`의 길이가 클 경우를 고려하여 `music`을 자르는 코드를 추가하니 해결할 수 있었다.

처음 문제를 읽을 때 완벽히 이해하자. 그렇지 않으면 아까운 시간이 소요된다..