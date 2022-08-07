# [86052] 빛의 경로 사이클

## Algorithm

- DFS (iterative)

## Logic

- 한 노드 잡고 BFS한다. 자식노드의 값을 부모의 노드값에 더해준다
- 자식 노드에서 부모 노드로 옮기는 비용은 자식노드값의 절댓값을 더해주면 된다.
- 옮기는 비용은 누적된 절댓값들의 합임.
- 어떤 노드에서 시작을 하든 움직이는 비용은 같다.

```javascript
function solution(a, edges) {
	let answer = BigInt(0);
	let map = new Array(a.length).fill().map(_ => []);
	const sum = a.reduce((acc, el, idx) => {
		return acc + el;
	}, 0);

	if (sum !== 0) return -1;
	edges.forEach(e => {
		let [a, b] = e;
		map[a].push(b);
		map[b].push(a);
	});

	let stack = [[0, -1]];
	let visited = Array(a.length).fill(false);

	while (stack.length !== 0) {
		let [cur, parent] = stack.pop();
		edges[map[cur]];

		if (visited[cur]) {
			answer += BigInt(Math.abs(a[cur]));
			a[parent] += a[cur];
			a[cur] = 0;
			continue;
		}

		stack.push([cur, parent]);
		visited[cur] = true;

		for (let child of map[cur]) {
			if (!visited[child]) stack.push([child, cur]);
		}
	}

	return answer;
}
```

## Review
Recursive로 하면 터진다.. 물론 내가 코드 잘못짠 줄 알았으나
BigInt도 써 줘야 하고.. Iterative하게 해 줘야 Call stack 초과가 안 난다고 한다.