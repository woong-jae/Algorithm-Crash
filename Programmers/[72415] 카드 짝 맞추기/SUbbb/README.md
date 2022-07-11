# [72415] 카드 짝 맞추기

## Algorithm
- 완전 탐색
- BFS
- 순열

## Logic

```java
private static int permutate(Card src) {
    int ret = Integer.MAX_VALUE;
    
    // 1부터 6까지의 카드가 존재할 수 있다.
    for (int n = 1; n <= 6; n++) {
        // n에 해당하는 카드를 저장하는 리스트
        ArrayList<Card> cards = findNCard(n);
        
        // 선택한 카드들이 없는 경우, 패스
        if (cards.isEmpty()) continue;
        
        // 현재 위치부터 선택한 카드까지 가기 위한 거리를 계산
        int one = bfs(src, cards.get(0)) + bfs(cards.get(0), cards.get(1)) + 2;
        // one의 역순으로 진행
        int two = bfs(src, cards.get(1)) + bfs(cards.get(1), cards.get(0)) + 2;
        
        // 뒤집은 카드를 보드에서 제거
        removeCards(cards);
        
        // 재귀 호출 수행
        // one으로 수행한 카드 뒤집기 이후, 커서가 card.get(1)에 있을 때 남은 카드들에 대한 뒤집기 수행
        ret = Math.min(ret, one + permutate(cards.get(1)));
        // two로 수행한 카드 뒤집기 이후, 커서가 card.get(0)에 있을 때 남은 카드들에 대한 뒤집기 수행 
        ret = Math.min(ret, two + permutate(cards.get(0)));
        
        // 1번 카드에 대한 뒤집기 수행 후, 2번 카드에 대한 뒤집기 진행 시에는 1번 카드 뒤집기에 대한 복원이 필요하다.
        restoreCards(cards, n);
    }
    
    if (ret == Integer.MAX_VALUE) return 0;
    
    return ret;
}
```
- 재귀를 수행하면서, 모든 카드 뒤집기 순서를 진행하면서 최소 비용을 계산한다.
- 카드와 카드 간 이동 시 최소 조작 횟수를 구하기 위해 BFS를 수행한다.

## Review
- 처음에 구현하려한 아이디어는, `map` 에 카드 번호별로 카드 위치를 저장하고, 현재 커서에서 가장 가까이 있는 카드를 뒤집고, 해당 카드 짝을 찾아 커서를 이동한 후 뒤집고를 반복하는 방식이었다.
  - 생각해보니 카드 순서가 중요할 수 있겠다라는 걸 깨달았고, 구현을 어떻게 해야 할 지 모르겠어서 ... 풀이 영상을 참고했다.