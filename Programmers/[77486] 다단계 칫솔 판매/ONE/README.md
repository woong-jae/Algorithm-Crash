# [77486] 다단계 칫솔 판매
## Algorithm
- **구현**

## Logic
- <자식, 부모>의 정보를 저장하는 Map, <이름, index>를 저장하는 Map 생성
- seller 배열을 반복문 돌면서 현재 나의 수입의 10분의 1을 부모수입으로 빼고 현재 나의 이익에 저장
- answer 에다 나의 이익을 더해주고
- 현재 사람을 부모로 바꿔준 후 현재 나의 수입을 10분의 1 해준다
- 만약 나눈 수입이 1보다 작다면 반복문을 그만둔다

```java
Map<String, String> parent = new HashMap<>();
Map<String, Integer> index = new HashMap<>();

for (int i = 0; i < n; i++) {
    parent.put(enroll[i], referral[i]);
    index.put(enroll[i], i);
}

for (int i = 0; i < sellerLen; i++) {
    String current = seller[i];
    // 벌어들인 순수익
    int revenue = amount[i] * 100;

    while (!current.equals("-")) {
        int parentProfit = revenue / 10;
        int currentProfit = revenue - parentProfit;

        answer[index.get(current)] += currentProfit;

        current = parent.get(current);
        revenue /= 10;

        if(revenue < 1)
            break;
    }
}
```

## Review
처음에는 class를 따로 선언하여 부모 자식간의 트리를 만들고 DFS로 했는데 이 문제는 각각 판매에서 10프로를 따져야 하는데
나는 한꺼번에 계산을 해서 자꾸 다른 답이 나왔고, 질문을 참고하여 수정했는데도 메모리 초과가 떠서 뭐지 싶었는데  
그냥 반복문만 돌리면되는 간단한 문제였다... 생각을 좀 하자..
