# [16172] 나는 친구가 적다 (Large)
## Algorithm
- KMP
## Logic
- 단순하게 배열에 알파벳만 넣어서 키워드가 있는지 찾으면 O(N^2)이 돼서 시간초과가 날 듯함
- 한번 탐색으로 알파벳만 남긴 후, 키워드 찾는 알고리즘으로 KMP를 사용하면 O(N)만으로 빠르게 발견 가능함
- KMP 메소드에선 알파벳만 남긴 ```string```에 ```pattern(키워드)```가 있는 인덱스 list를 반환받음
- 해당 list가 비었으면 0 반환/안 비었으면 1 반환
```python
def kmp(string, pattern):
    table = [0 for _ in range(len(pattern))]
    i = 0
    for j in range(1, len(pattern)):
        while i > 0 and pattern[i] != pattern[j]:
            i = table[i - 1]
        if pattern[i] == pattern[j]:
            i += 1
            table[j] = i

    result = []
    i = 0
    for j in range(len(string)):
        while i > 0 and pattern[i] != string[j]:
            i = table[i - 1]
        if pattern[i] == string[j]:
            i += 1
            if i == len(pattern):
                result.append(j - i + 1)
                i = table[i - 1]
    return result
```

## Review
키워드 길이가 커서 바로 KMP 적용을 생각할 수 있었다. 근데 KMP 알고리즘 자체는 구글링함ㅎ 이건 암기해야될 듯