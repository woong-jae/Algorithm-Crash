# [17677] 뉴스 클러스터링
## Algorithm
- 문자열
## Logic
- 원소 중복을 허용하는 다중집합이므로, 2글자씩 나눈 원소들을 딕셔너리에 빈도로 저장하면 쉽게 풀 수 있음.
- 집합1, 집합2를 탐색하면서 교집합을 구한 후, ```합집합 = 집합1 + 집합2 - 교집합``` 연산으로 합집합 수를 구하면 됨.
1. 문자열1, 2를 대문자로 바꾸고 2글자씩 나눠서 딕셔너리에 저장함.
```python
def make_dict(s, set):
    global sum
    for i in range(len(s) - 1):
        tmp = s[i] + s[i + 1]
        if not tmp.isalpha():
            continue
        sum += 1
        if tmp not in set:
            set[tmp] = 1
        else:
            set[tmp] += 1
```
- 공백/특수문자가 있는 원소는 버림.
- 합집합 원소 수를 구하기 위해서 ```sum```에 집합1, 2 원소 수를 더함.
2. 집합1, 2 모두 공집합이면 자카드 유사도 1임.
3. 집합1의 원소들이 집합2에 있는지 탐색하면서 교집합 개수 구합.
```python
for i in set1:
    if i in set2:
        join += min(set1[i], set2[i])
```
- 집합2에 있으면 교집합 원소 수를 증가시킴. 교집합은 해당 원소 개수가 더 작은 쪽으로 따름.
4. 합집합 개수를 구하고, 자카드 유사도 구함.
```python
answer = join / (sum - join)  
```

## Review
쉬웠는데 공백/특수문자를 그냥 없애고 시작한다는 걸로 잘못 이해해서 삽질했다; 그냥 버리면 되는 거였음
