# [1865] 웜홀

## Algorithm

- BellmanFord

## Logic

벨만 포드를 수행하여 음의 사이클이 나타나면 'YES', 그렇지 않으면 'NO를 출력한다.

- 이때, 모든 지점을 시작 지점으로 두어 확인해 볼 필요가 없다.

- 시작 지점은 아무 곳이나 두어 음의 사이클만 확인하면 된다.

- 단, 일반 벨만 포드처럼 `Infinity` 값일 경우 `continue` 하는 조건이 있으면 안된다.

  - 해당 조건은 특정 시작지점으로부터의 최소 거리를 구하기 위해 필요한 것이므로.

## Review

음의 사이클을 확인하는 벨만 포드는 생각치 못했다.

다른 사람의 접근법을 참고하여 코드를 작성했으나, 계~속 틀렸다.

문제는 `Infinity` 였다. 

`Infinity`에 특정 수를 연산하면 `Infinity`가 그대로 나온다..

그래서 문제에서 제시한 최대값인 `10000`으로 대체하니 통과할 수 있었다.
