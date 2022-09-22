# [1509] 팰린드롬 분할

## Algorithm
- DP

## Logic

```java
void setPalindrome(String string)
```

- `boolean[][] palindrome` 배열에 해당 범위의 문자열이 팰린드롬 수인지를 저장하는 함수

```java
int getMin()
```

- 생성한 `boolean` 배열을 읽으면서 최소 분할 개수를 구하고 반환하는 함수

## :black_nib: **Review**

- DP임은 쉽게 유추할 수 있었으며, 초기 아이디어는 다음과 같았다.
  - `int[][] dp` 하나만 사용
  - `dp[][]` 의 값은 그 범위에 해당 문자열을 이루는 최소 팰린드롬 수를 의미
  - 그리고 길이 1부터 N까지 범위를 늘려가면서 `dp` 배열 값을 갱신한다.
- 몇 개의 반례를 통해 로직을 수정해보면서 구현했는데, ABCDD와 같은 반례에서 막혀버려 풀이를 참고했다.