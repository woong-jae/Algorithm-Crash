function getPermutation(arr, count) {
  if (count === 1) {
    return arr.map((el) => [el]);
  }

  const result = [];

  arr.forEach((fixed, index, origin) => {
    const permutation = getPermutation(origin, count - 1);
    const attached = permutation.map((el) => [fixed, ...el]);

    result.push(...attached);
  });

  return result;
}

function getTotalPrice(sales, saledPrices, limitSale) {
  const totalPrice = sales.reduce((acc, sale, i) => {
    if (limitSale <= sale) {
      return acc + saledPrices[i];
    }
    return acc;
  }, 0);

  return totalPrice;
}

function solution(users, emoticons) {
  const permutation = getPermutation([10, 20, 30, 40], emoticons.length);

  const answers = permutation.map((sales) => {
    const result = [0, 0];

    const saledPrices = emoticons.map((price, i) => {
      return price - (price / 100) * sales[i];
    });

    users.forEach(([limitSale, limitPrice]) => {
      const totalPrice = getTotalPrice(sales, saledPrices, limitSale);

      if (limitPrice <= totalPrice) {
        result[0] += 1;
        return;
      }

      result[1] += totalPrice;
    });

    return result;
  });

  const answer = answers.reduce(
    (acc, cur) => {
      if (cur[0] > acc[0]) {
        return cur;
      }
      if (cur[0] === acc[0] && cur[1] > acc[1]) {
        return cur;
      }
      return acc;
    },
    [0, 0]
  );

  return answer;
}
