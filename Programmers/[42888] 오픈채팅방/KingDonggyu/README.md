# [42888] 오픈채팅방

## Algorithm

- map

## Logic

- recored 순회

  - 원소를 공백을 기준으로 잘라 첫 단어, 유저 아이디, 닉네임을 구별한다.

  - 첫 단어가 "Change" 또는 "Enter" 이면 map에 유저 아이디 키의 값을 닉네임으로 설정한다.

  - 이후, 첫 단어가 "Enter" 또는 "Leave" 이면 in-out 기록을 나타내는 배열에 `{ id: 유저 아이디, action: 첫 단어 }` 객체를 push한다.

- in-out 기록을 나타내는 배열 순회

  - 원소(객체)의 `id`를 키로하여 map에 저장된 값, 즉 닉네임을 가져온다.

  - 원소의 `action`에 해당하는 문자열을 answer 배열에 push한다.

### 시간 복잡도: O(N)

## Review

최근 여러 프로젝트로 인해 알고리즘 문제가 밀려서 걱정했었는데, 연속으로 쉬운 문제가 나와서 좋다.