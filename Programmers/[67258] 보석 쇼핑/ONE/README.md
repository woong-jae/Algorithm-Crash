# [67258] 보석 쇼핑
## Algorithm
- **Two Pointer**
## Logic
- 핵심 아이디어는 `Queue` 에 보석들을 넣으면서 **맨앞에 보석**이 구간에 2개이상 존재한다면 제거하면서
- 출발점을 뒤로 당기고 보석 종류의 개수(`Set`의 크기) 와 `Map`의 크기가 같아지면
- `Queue`(해당 구간)에 모든 종류의 보석이 들어있다는 뜻이므로 구간의 길이를 비교하여 갱신해준다 

```java
for (String gem : gems) {
    map.put(gem, map.getOrDefault(gem, 0) + 1);

    queue.add(gem);

    while (true) {
        String head = queue.peek();

        // Queue의 맨앞에 있는 보석이 Map에서 2개 이상이면
        // 맨앞의 보석을 빼주고 출발점을 뒤로 한칸 땡긴다
        if (map.get(head) > 1) {
            map.put(head, map.get(head) - 1);
            queue.poll();
            temp++;
        }

        else break;
    }

    // Map 과 Set이 사이즈가 같다는 건 모든 보석이 구간에 있다는 이야기고
    // 현재 찾은 구간들중 가장 작은 구간이면 최소 구간을 갱신하고
    // 임시 시작점이었던것을 정답 시작점으로 해준다
    if (map.size() == set.size() && len > queue.size()) {
        len = queue.size();
        start = temp;
    }
}
```

## Review
혼자서 끙끙대며 중복없는 보석의 목록을 가지고 한개씩 어떻게 하겠다는 아이디어는   
생각했지만 결국엔 생각이 나지않아 다른사람의 코드를 참조하였다  
거의 아이디어에 근접했었는데 매우 아깝다고 생각하고  
참 좋은 문제였다고 생각한다! 다음에 나오면 절대 안틀린다
