# [86052] 빛의 경로 사이클

## Algorithm

- 투포인터

## Logic

- cookie 배열에서 middle을 하나 잡고 거기서 좌우로 가면서 leftSum rightSum을 구한다
- 그래서 같으면 curMax에

```javascript
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
```

## Review
발상은 그렇게 어렵진 않은데 효율성 검사랑 시간 검사가 너무 빡세다.
아니 --랑 ++를 동시에 해 주는 거랑.. 그런 거랑 변수 하나 지정하는 게 검사가 되나보다

그래도 효율성 검사 하나 틀리길래.. 보니까

curMax랑 newMax가 생기는 경우 비교해 주려고 했는데 생각해보면 바구니 안에 음수가 없다 보니 newMax가 curMax보다 작아질 일이 없다.

그래서 빼고 나니까 통과..