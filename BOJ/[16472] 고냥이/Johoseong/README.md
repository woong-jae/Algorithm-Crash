# [16472] 고냥이
## Algorithm
- two pointer
## Logic
- 문자열 범위가 커서 이중 for문은 안됨. start와 end로 인덱스를 저장하면서 최대 길이를 갱신해주는 투포인터 알고리즘 사용해야함.
- 중요한 점은, 알파벳 종류 ```dict()```의 value를 해당 알파벳이 마지막으로 발견된 인덱스로 갱신해줘야 한다는 점. 그래서 새로운 알파벳이 발견돼서 기존 알파벳 중 하나 뺄 때, 가장 작은 value를 갖는 알파벳을 빼주고 start는 그 다음 인덱스를 가르키게 하면 됨.
1. end를 이동하면서 알파벳을 확인함.
2. 새로운 알파벳이고 현재 알파벳 개수가 N 안넘음 -> 알파벳 딕셔너리에 추가.
```python
if (string[end] not in alphabet):
    if len(alphabet) < N:
        alphabet[string[end]] = end
```
3. 새로운 알파벳인데 현재 알파벳 개수가 이미 N임 -> value 제일 작은 알파벳 하나 빼주고 지금 알파벳 추가 & start 갱신.
```python
if (string[end] not in alphabet):
...
    else:
        tmp = 100000
        for al in alphabet:
            e = alphabet[al]
            tmp = min(tmp, e)
            if tmp == e:
                tmp_al = al
        alphabet.pop(tmp_al)
        start = tmp + 1
        alphabet[string[end]] = end
```
4. 이미 있는 알파벳 -> 해당 알파벳의 value값 end로 갱신.
```python
alphabet[string[end]] = end
```
- alphabet 딕셔너리 탐색은 최대 26라서 무시하면 시간복잡도 O(N).

## Review
2주차 때 보석쇼핑 문제랑 비슷해서 투포인터 쓰니까 잘 풀 수 있었다.
