# [150368] 이모티콘 할인행사
## Algorithm
- **DFS**, **Priority Queue**

## Logic
- 구현한 객체는 User(사용자 객체), Emoticon(이모티콘 객체), Result(우선순위에 따른 결과를 위한 객체)
- 각 이모티콘들의 할인율들의 변동을 DFS 를 사용한 완전 탐색으로 구현
```java
private void simulate(int depth, int n) {
    if (depth == n) {
        results.add(getResult());
        return;
    }
    for (int rate : rates) {
        emoticons.get(depth).changeRate(rate);
        simulate(depth + 1, n);
    }
}
```
- 이모티콘들의 결과를 가지고 사용자가 행동한 결과는 총 2가지가 있다
  1. 이모티콘을 모두 구매한 금액이 사용자의 상한선을 넘지 않을 떄 -> 이모티콘 플러스를 구독하지 않고, 매출이 발생
  2. 이모티콘을 모두 구매한 금액이 사용자의 상한선을 넘었을 때 -> 이모티콘 플러스를 구독하고, 매출이 발생하지 않음
```java
int subscreibers = 0;
int sales = 0;
for (User user : users) {
    int purchasePriceOfEmoticons = user.getPurchasePriceOfEmoticons(emoticons);
    if (user.purchasePrice() <= purchasePriceOfEmoticons) {
        subscreibers++;
    } else {
        sales += purchasePriceOfEmoticons;
    }
}
```
- 위의 결과를 우선순위 큐에 삽입하여 마지막에 제일 앞에 있는 Result를 도출

## Review
쉽게 푼 문제!
