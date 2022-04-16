# [64065] 튜플
## Algorithm
- 문자열
## Logic
- 먼저 문자열을 쪼개서 집합 별로 ```tuples``` 배열에 담음. 문자열은 제일 바깥 중괄호를 제외하고 ```},{```로 자르면 예쁘게 잘림.
- ```tuples```를 길이 기준 정렬. 
- 집합들은 튜플의 첫 원소부터 바로 다음 원소를 추가해가며 만들어진 것이므로, 정렬된 ```tuples```를 탐색하면서 ```answer```에 없는 원소는 추가함.
```python
for i in range(len(tuples)):
    tuple_set = tuples[i].split(',')

    for j in tuple_set:
        if j not in answer:
            answer.append(j)
```
- 이 때, 집합 하나 자체는 정렬된게 아니라서 answer에 없는 원소를 for문으로 탐색해야함.
- 시간복잡도 O(N^3)

## Review
전체 알고리즘보다 문자열 어떻게 쪼갤지 고민이 많았다. 그런데 다 풀고보니까 O(N^3)이나 돼서 다른 풀이 생각해봐야겠다. 정규표현식 사용하면 더 빨라질 것 같아서 해보기.
