# [81303] 표 편집
## Algorithm
- Linked List
## Logic
먼저 효율성이 떨어진 코드는 주어진 명령어대로 모두 수행한 후, 스택에 있는 정보에 따라 배열을 복구하면서 없는 것을 채워넣는 방법을 사용했다.

```js
let answer = "O".repeat(n);
while(stack.length) {
    const restoreIndex = stack.pop();
    answer = answer.slice(0, restoreIndex) + "X" + answer.slice(restoreIndex);
}
```
다른 사람들의 코드를 보니 자바로 짰을 경우 삽입하는 메서드의 효율이 좋아서 이 정도로도 충분히 통과가 된 것 같다.

하지만 자바스크립트는 안된다. `slice`를 스택의 길이만큼 하기 때문에 최악의 경우 O(N^2)의 시간복잡도를 가진다.

여기서 개선할 수 있는 것은 복구하는 연산이다.

이는 표를 양방향 연결리스트로 표현함으로써 가능하다.
연결 리스트의 노드는 스택에 들어가도 이전 노드와 다음 노드의 정보를 알고있기 때문에 복원시 적절하게 연결만 시켜주면 된다.
따라서 복원하는데 O(1)의 시간복잡도, 이를 정답에 반영하는데 O(N)의 시간복잡도를 가져 전체적으로 O(N)으로 개선할 수 있다.

```js
const stack = [];
let cur = head;
while(k--) cur = cur.next;
cmd.forEach(command => {
    let [dir, x] = command.split(" ");
    if(dir === "C") {
        stack.push(cur);
        // head 일 때
        if(cur.prev) cur.prev.next = cur.next;
        // 마지막 노드일 때
        if(cur.next) {
            cur.next.prev = cur.prev;
            cur = cur.next;
        }
        else cur = cur.prev;
    }
    else if(dir === "Z") {
        const restore = stack.pop();
        // head 일 때
        if(restore.prev) restore.prev.next = restore;
        // 마지막 노드일 때
        if(restore.next) restore.next.prev = restore;
    }
    else {
        if(dir === "U") while(x--) cur = cur.prev;
        else while(x--) cur = cur.next;
    }
});
```

## Review
개선할 방법을 도저히 모르겠어서 다른 사람의 코드를 봤다. 
연결 리스트는 전혀 생각도 못했다... 특히 연결 리스트를 활용하는 문제를 한 번도 안풀어봐서 그런지 고려하지도 않았다.

앞으로 삭제나 삽입, 복원하는 것을 최적화하는 문제는 연결리스트를 꼭 떠올리자!