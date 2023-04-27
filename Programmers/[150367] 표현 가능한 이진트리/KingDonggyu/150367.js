function getExtraZeroCount(binary) {
  const nextBinary = binary.length.toString(2);
  return 2 ** nextBinary.length - 1 - binary.length;
}

function checkIsBinaryTree(treeString, start, end) {
  if (start === end) {
    return 1;
  }

  const parent = Math.floor((start + end) / 2);
  const leftChild = Math.floor((start + parent - 1) / 2);
  const rightChild = Math.floor((end + parent + 1) / 2);

  if (
    treeString[parent] === '0' &&
    (treeString[leftChild] === '1' || treeString[rightChild] === '1')
  ) {
    return 0;
  }

  if (!checkIsBinaryTree(treeString, start, parent - 1)) {
    return 0;
  }

  if (!checkIsBinaryTree(treeString, parent + 1, end)) {
    return 0;
  }

  return 1;
}

function solution(numbers) {
  const answer = numbers.map((number) => {
    const binary = number.toString(2);
    const extraZeroCount = getExtraZeroCount(binary);
    const treeString = '0'.repeat(extraZeroCount) + binary;

    return checkIsBinaryTree(treeString, 0, treeString.length - 1);
  });

  return answer;
}
