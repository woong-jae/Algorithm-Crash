# [77486] 다단계 칫솔 판매 - Python

## 🔍 Algorithm
**Linked List**

## 💻 Logic

```Python
    link = defaultdict(list)    # 연결리스트(반대 방향)
    amount_dict = defaultdict(int)  # 판매원별 이익금 합계
    # 연결리스트 생성
    for i, r in enumerate(referral):
        if r == "-": continue
        link[enroll[i]].append(r)
```
- **`link` : 연결 리스트(반대 방향)**  
- **`amount_dict` : 판매원별 이익금 합계**  
- **`link` 딕셔너리 이용하여 연결 리스트 생성**  
    **판매원 -> 추천인** 순으로 연결되도록 연결  
    "-"인 경우는 생략  

```Python
    # 이익금 배분
    for i, a in enumerate(amount):
        cost = a * 100
        name = seller[i]
        while cost > 0:
            amount_dict[name] += cost - int(cost * 0.1)
            cost = int(cost * 0.1)
            if len(link[name]) == 0: break
            name = link[name][0]
```
- **`amount`에 있는 정보대로 이익금 계산해서 누적시키면서 배분**  
    문제에 나와있는 것처럼 이익금(`cost`)는 **10%**씩 넘기면서 계산  
    `cost`가 **0 이하**가 되면 **break**  
    `link` 연결 리스트 끝에 도달하면(해당 리스트가 비어있으면) **break**  


## 📝 Review

이익금을 계산하기 위해서는 leaf 노드에서부터 올라가면서 이익금을 10%씩 위로 넘겨줘야겠다고 생각했고, 그래서 딕셔너리를 연결 리스트 형태로 만들어서 구현했다.  
이익금이 1원 미만이면 배분하지 않고 자신이 모두 가진다라는 말을 까먹어서 시간초과가 났고 바로 고쳤는데, 테스트케이스를 주지 않는 코테 생각하면 이런 실수 줄이고 차분히 문제 읽도록 더 노력해야겠다..  
