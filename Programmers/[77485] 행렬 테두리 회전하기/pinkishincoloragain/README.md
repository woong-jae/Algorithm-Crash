# [77485] 행렬 테두리 회전하기

## Algorithm

- 구현
- Queue

## Logic

- 돌릴 거를 Queue에 넣어 준다
- Queue에 넣고 pop 후 unshift로 한 칸씩 밀어준다
- 최소값을 찾는다
- 그리고 다시 돌린 걸 배열한다

```javascript
queries.forEach(query => {
  const [x1, y1, x2, y2] = query.map(pos => pos - 1);
  let queue = [];

  for (let i = 0; i < y2 - y1; i++) queue.push(matrix[x1][y1 + i]);
  for (i = 0; i < x2 - x1; i++) queue.push(matrix[x1 + i][y2]);
  for (i = 0; i < y2 - y1; i++) queue.push(matrix[x2][y2 - i]);
  for (i = 0; i < x2 - x1; i++) queue.push(matrix[x2 - i][y1]);
  // console.log(queue);
  queue.unshift(queue.pop());
  answer.push(Math.min(...queue));
  console.log(queue);

  for (i = 0; i < y2 - y1; i++) matrix[x1][y1 + i] = queue.shift();
  for (i = 0; i < x2 - x1; i++) matrix[x1 + i][y2] = queue.shift();
  for (i = 0; i < y2 - y1; i++) matrix[x2][y2 - i] = queue.shift();
  for (i = 0; i < x2 - x1; i++) matrix[x2 - i][y1] = queue.shift();
});
```

## Review

이걸 queue로 돌릴 생각을 하는 건,. 멋지다
진짜.. 아직 너무 부족하네

