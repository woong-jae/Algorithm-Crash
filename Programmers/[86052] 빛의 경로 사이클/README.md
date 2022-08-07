# [86052] 빛의 경로 사이클

## Algorithm

- 구현

## Logic

- 구현

```javascript
function solution(grid) {
	const answer = [];

	const dx = [-1, 1, 0, 0];
	const dy = [0, 0, -1, 1];

	const ch = Array.from({ length: grid.length }, () => []).map(c => {
		for (let i = 0; i < grid[0].length; i++) c.push([0, 0, 0, 0]);
		return c;
	});

	for (let x = 0; x < grid.length; x++) {
		for (let y = 0; y < grid[0].length; y++) {
			for (let d = 0; d < dx.length; d++) {
				if (ch[x][y][d]) continue;
				const cnt = checker(x, y, d);
				if (cnt) answer.push(cnt);
			}
		}
	}

	const checker = (x, y, d) => {
		let cnt = 0;
		while (true) {
			if (ch[x][y][d]) break;
			ch[x][y][d] = 1;
			cnt++;

			x = x + dx[d];
			y = y + dy[d];
			if (x < 0) x = grid.length - 1;
			if (x >= grid.length) x = 0;
			if (y < 0) y = grid[0].length - 1;
			if (y >= grid[0].length) y = 0;
			d = getNextDir(grid[x][y], d);
		}

		return cnt;
	};

	return answer.sort((a, b) => a - b);
}

function getNextDir(block, dir) {
	if (block === "S") return dir;
	if (block === "L") return [2, 3, 1, 0][dir];
	if (block === "R") return [3, 2, 0, 1][dir];
}
```

## Review
상하좌우 갈라서 방문한 노드를 저장하는데.. 자바스크립트 코드는 너무 길어진다
코테용은 정말 아닌듯.
