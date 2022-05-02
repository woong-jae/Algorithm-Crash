# [81303] 표 편집
## Algorithm
- **Linked List**
## Logic
- 전체적인 구조는 `양방향 Linked List` 를 사용하며  
- 삭제한 행의 정보는 가장 최근에 삭제한 행부터 가져와야 하기에 `Stack` 을 활용해 구현한다  
  

- 노드들을 양방향 연결리스트로 이전 노드의 인덱스와 다음 노드의 인덱스 정보를 저장
- 마지막 행은 다음 노드를 -1로 지정
```java
for (int i = 0; i < n - 1; i++)
    list.add(new Row(i - 1, i + 1));
list.add(new Row(n - 2, -1));
```

- 위 또는 아래로 이동할때에는 리스트의 원소들을 이동하는 것이 아닌 해당 노드의 이전 노드 또는 다음 노드의 인덱스를 타면서 이동
```java
private int move(int k, int num, String command, ArrayList<Row> list) {
    int index = k;

    if (command.equals("U"))
        for (int i = 0; i < num; i++)
            index = list.get(index).previous;

    else
        for (int i = 0; i < num; i++)
            index = list.get(index).after;

    return index;
}
```
- 삭제할 때는 실제 그 노드를 지우는 것이 아닌 앞뒤의  연결 관계를 바꾸어준다
- 삭제를 할떄 마지막 노드라면 k 의 값을 이전 노드의 인덱스로 바꾸어준다
- 복구를 할때 해당 인덱스의 앞뒤의 연결 관계를 원래대로 돌려준다
- 삭제와 복구에서 맨앞 맨뒤의 예외처리를 해주어야 한다
```java
for (String s : cmd) {
    String[] tokens = s.split(" ");

    switch (tokens[0]) {
        case "D":
        case "U":
            k = move(k, Integer.parseInt(tokens[1]), tokens[0], list);
            break;
        case "C":
            deletedNum.add(k);

            if(list.get(k).after == -1){
                list.get(list.get(k).previous).after = -1;
                k = list.get(k).previous;
                break;
            }

            if(list.get(k).previous != -1)
                list.get(list.get(k).previous).after = list.get(k).after;
            list.get(list.get(k).after).previous = list.get(k).previous;

            k = list.get(k).after;
            break;
        case "Z":
            int top = deletedNum.pop();
            if(list.get(top).after != -1)
                list.get(list.get(top).after).previous = top;
            if(list.get(top).previous != -1)
                list.get(list.get(top).previous).after = top;
    }
}
```

## Review
최근에 푼 문제중에서 제일 근처도 못갔던 문제였던것 같다  
계속 잡고있다가 질문하기에서 링크드리스트를 사용해야한다는 것을 확인하고 풀었다  
코드자체가 어려운 것이 아닌 링크드리스트라는 아이디어를 생각해내는게 중요했던 것 같다  
투포인터와 더불어 익숙해져야할 문제이다...
