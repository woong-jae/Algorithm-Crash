# [72415] 카드 짝 맞추기
## Algorithm
- **Permutation**, **구현**, **BFS**

## Logic
- 순열로 뒤집을 카드의 제거 순서를 정한다
- 카드 제거 순서에 따라 제거
  - 각 카드 인덱스별 2개의 위치를 one 먼저 제거할 것인지, two 먼저 제거할 것인지를 결정
- 키보드 입력을 BFS 로 이동
  - 상하좌우 & (Ctrl + 상하좌우)

```java
private void visit(int[][] map, int[] visitCase, int x, int y, int index, int count) {
    if (count > min)
    {
        return;
    }
    if (index == visitCase.length) {
        min = Math.min(count, min);
        return;
    }

    ArrayList<Card> cards = cardPos.get(visitCase[index]);
    Card card1 = cards.get(0);
    Card card2 = cards.get(1);


    int nextCnt;
    nextCnt = count  + getDistance(map, x, y, card1, card2);
    visit(map, visitCase, card2.x, card2.y, index + 1, nextCnt);
    map[card1.x][card1.y] = card1.index;
    map[card2.x][card2.y] = card2.index;
    nextCnt = count  + getDistance(map, x, y, card2, card1);
    visit(map, visitCase, card1.x, card1.y, index + 1, nextCnt);
    map[card1.x][card1.y] = card1.index;
    map[card2.x][card2.y] = card2.index;

}
```

## Review
혼자서 2시간 생각하다가 도저히 못풀겠어서 다른사람의 풀이를 참고했다  
카드의 순서를 순열로 하는 것까지는 생각했는데 컨트롤을 포함한 BFS와 이동수의 최솟값을 구하기가 어려웠다  
그냥 어지러운 문제. 실전에서 나오면 못풀듯!