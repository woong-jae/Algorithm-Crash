## :mag: Algorithm

BruteForce, Stack

## :round_pushpin: Logic

```js
const expArr = [];
const isOperator = { '*': false, '+': false, '-': false };

// 문자열을 배열로 변환
let num = '';

expression.split('').forEach((op) => {
  if (['*', '+', '-'].includes(op)) {
    expArr.push(+num);
    expArr.push(op);
    isOperator[op] = true;
    num = '';
  } else {
    num += op;
  }
});
expArr.push(+num);
```

- 먼저, 주어진 문자열을 배열로 변환한다.

- 그리고 어떤 연산자가 존재하는지 `isOperator` 객체에 저장한다.

<br />

```js
// 주어진 연산자 확인
const operators = [];

if (isOperator['*']) operators.push('*');
if (isOperator['+']) operators.push('+');
if (isOperator['-']) operators.push('-');

const permutations = getPermutaion(operators, operators.length);
```

- `isOperator` 객체를 통해 연산자의 존재를 파악하고, 우선순위 **순열**을 구한다.

<br />

```js
let answer = 0;

permutations.forEach((permutation) => {
  let copyExpArr = [...expArr];

  permutation.forEach((p) => {
    let stack = [];
    let operator = null;

    copyExpArr.forEach((element) => {
      if (element === p) {
        operator = p;
      } else if (operator) {
        stack.push(calc(operator, stack.pop(), element));
        operator = null;
      } else {
        stack.push(element);
      }
    });

    copyExpArr = stack;
  });

  answer = Math.max(Math.abs(copyExpArr[0]), answer);
});

return answer;
```

- 주어진 순열을 통해 **stack**을 이용하여 모든 경우의 결과를 확인한 후, 절대값이 가장 큰 값을 리턴한다.

<br />

## 시간 복잡도

```js
permutations.forEach((permutation) => { // (1)
  ...
  permutation.forEach((p) => { // (2)
    ...
    copyExpArr.forEach((element) => { // (3)
      ...
```

- (1)의 경우 = O(6) (최대 순열의 개수가 6이므로)

- (2)의 경우 = O(3) (최대 연산자 개수가 3이므로)

- (3)의 경우 = O(N)

따라서, 시간복잡도는 O(6 * 3 * N) 이기 때문에, **O(N)** 이다.

## :memo: Review

원래 순열을 통한 완전 탐색을 하지 않으려 했다. 더 효율적인 방법이 있을 것이라 생각했기 때문에.

생각해보니 연산자 개수는 최대 3개로 정해져 있고 이에 따른 최대 순열 개수는 6개 이므로, 완전 탐색을 수행해도 시간에 큰 영향을 끼치지 않을 것이라 판단했다.

순열을 이용한 완전탐색 아이디어가 떠오를땐, 주어진 입력 케이스를 보고 효율성을 판단해보면 좋을 것 같다.