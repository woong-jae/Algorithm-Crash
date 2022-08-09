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

console.log(getNumber("0111111010"));
