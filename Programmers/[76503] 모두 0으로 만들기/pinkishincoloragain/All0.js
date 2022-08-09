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

console.log(
	solution(
		[-5, 0, 2, 1, 2],
		[
			[0, 1],
			[3, 4],
			[2, 3],
			[0, 3],
		]
	)
);
