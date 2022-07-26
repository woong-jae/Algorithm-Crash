# [42893] 매칭 점수

## Algorithm

- regular expression

- map

## Logic

- 정규 표현식을 통해 url과 외부 링크, 기본점수를 얻는다.

```js
pageinfo.set(url, {
  index, url, links, baseScore,
  matchingScore: baseScore
});
```

- 위와 같은 형식의 객체를 값으로 하고, url을 키로 하여 map에 추가한다.

- map을 순회하며 외부 링크들의 `matchingScore` 프로퍼티에 자신의 링크점수를 계산하여 더한다.

### 시간 복잡도: O(N^2)

## Review

정규표현식을 이용하여 쉽게 해결했다.

그런데 하나 이상한 점이, 태그 이름이 word로 입력하는 테스트케이스는 없는가 보다.

주어진 page의 모든 영문자를 word와 비교하여 기본점수를 구하도록 했기에, word가 'a'나 'body', 'html' 로 주어지면 내 코드는 틀린다.