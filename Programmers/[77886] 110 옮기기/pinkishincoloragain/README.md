# [77886] 110 옮기기

## Algorithm

- 스택

## Logic

- 시간 복잡도 N 안에 구해야 안 터짐.
- s 문자열을 왼쪽부터 돌면서 문자 하나 받을 때마다 stack에 있는 거랑 비교.
- stack에 1 1 0 순서로 쌓이면 cnt 추가함.


```javascript
function getNumber(s) {
	const stack = [];
	const arr = s.split("");
	let cnt = 0;

	for (let i = 0; i < arr.length; i++) {
		const cur = arr[i];

		if (stack.length > 1) {
			const a = stack.pop();
			const b = stack.pop();

			if (b === "1" && a === "1" && cur === "0") {
				cnt++;
				continue;
			} else {
				stack.push(b, a, cur);
			}
		} else {
			stack.push(cur);
		}
	}

	if (cnt === 0) {
		return s;
	} else {
		const list = [];
		const keyword = "011";

		while (stack.length) {
			const last = stack.pop();

			if (last === "0") {
				stack.push(last);
				break;
			}

			list.push(last);
		}

		while (cnt) {
			list.push(...keyword);
			cnt--;
		}

		while (stack.length) {
			list.push(stack.pop());
		}

		const result = list.reverse().join("");
		return result;
	}
}

function solution(s) {
	return s.map(st => getNumber(st));
}
```

## Review
풀이1. 처음에는 1 1 0 순서를 세아려서 수학적으로 풀려고 했다.
풀이2. 그 다음에는 왼쪽부터 110이 쌓이면 안 빼고 그냥 계산하는 걸로 하려고 했다
풀이3. 그 다음에는 110이 새로 생기면 바로 넣으려고 했다

그러다가 풀이를 봐 버렸다.. 이게 맞구나..
다시 짜면 pop을 안 해도 돼서 조금 나을 것 같은데
