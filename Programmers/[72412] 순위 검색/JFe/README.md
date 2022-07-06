# [72412] 순위 검색 - Python

## 🔍 Algorithm
**Product, Binary Search**

## 💻 Logic

```Python
# 중복 순열로 key 조합 생성 후, 딕셔너리에 값 추가
    for i in info:
        l = i.split()
        prod_list = list(product([l[0], '-'], [l[1], '-'], [l[2], '-'], [l[3], '-']))
        for p in prod_list:
            info_dict[''.join(p)].append(int(l[-1]))
```
- **중복 순열로 key 조합 생성 후, 딕셔너리에 값 추가**  
    - `'-'`도 고려해야 하기 때문에 `product`로 **중복 순열**을 만들어서 딕셔너리 key로 사용  

```Python
# 딕셔너리 정렬
    for key in info_dict.keys():
        info_dict[key].sort()
```
- **딕셔너리 정렬**  

```Python
# query를 딕셔너리 key 형태로 맞춰주고, 점수로 이진 탐색
    for q in query:
        q_list = q.split()
        key = ''.join([q_list[0], q_list[2], q_list[4], q_list[6]])
        index = bisect.bisect_left(info_dict[key], int(q_list[-1]))
        count = len(info_dict[key]) - index
        answer.append(count)
```
- **query를 딕셔너리 key 형태로 맞춰주고, 점수로 이진 탐색**  
    - `bisect` 이용해서 이진 탐색으로 해당 점수 위치 찾기


## 📝 Review

레벨 2치고는 난이도가 있었던 문제 같다,,  
처음에는 `combinations`로 접근했다가 '-' 처리를 쉽게 하기 위해 `product` 사용했다.  
지금까지 이진 탐색 문제들은 직접 구현해서 풀었었는데 이번에는 bisect라는 라이브러리가 있는 걸 보고 사용해봤는데 굿굿굿  
