function solution(cookie) {
	let [left, right, leftSum, rightSum] = [0, 0, 0, 0];
	let curMax = 0;

	for (let middle = 0; middle < cookie.length; middle++) {
		[left, right] = [middle, middle + 1];
		leftSum = cookie[left];
		rightSum = cookie[right];

		while (true) {
			if (leftSum === rightSum && curMax < leftSum) {
				curMax = leftSum;
			} else if (leftSum <= rightSum && left !== 0) {
				leftSum += cookie[--left];
			} else if (rightSum < leftSum && right !== cookie.length - 1) {
				rightSum += cookie[++right];
			} else {
				break;
			}
		}
	}
	return curMax;
}

console.log(solution([1, 1, 2, 3]));
console.log(solution([1, 2, 4, 5]));
