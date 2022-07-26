# [17686] 파일명 정렬
## Algorithm
- 정규표현식, 정렬
## Logic
- 정규표현식 써서 head부와 number부를 나눈 후, head를 먼저 정렬하고 중복 발생한 head값들만 number를 비교하며 정렬해주었음.
1. 정규표현식으로 head, number 분리 후 딕셔너리에 files의 인덱스를 key로 저장
```python
for i, f in enumerate(files):
    tmp1 = re.findall('[^0-9]+', f)
    tmp2 = re.findall(r'\d+', f)
    h = tmp1[0].upper()
    head[i] = h
    number[i] = int(tmp2[0])
    sames.setdefault(h, [])
    sames[h].append(i)
```
- ```sames```에는 head 동일한 파일명들의 인덱스 저장
2. head 기준 정렬
```python
head = sorted(head.items(), key=lambda x:x[1])
```
3. head 동일한 파일명들은 number 비교해서 새로 정렬
```python
for h in head:
    same_head = sames[h[1]]
    if files[h[0]] in answer:
        continue

    if len(same_head) > 1:
        tmp = dict()
        for i in same_head:
            tmp[i] = number[i]
        tmp = sorted(tmp.items(), key=lambda x:x[1])
        for i in tmp:
            answer.append(files[i[0]])
    else:
        answer.append(files[same_head[0]])
```

## Review
정규표현식으로 풀어야겠다는 생각만 하고 두서없이 풀었더니 코드가 너무너무 더러워졌다.. 무난하긴했는데 정리해야할듯..