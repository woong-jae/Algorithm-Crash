function getCombination(arr, count) {
  if (count === 1) {
    return arr.map((el) => [el]);
  }

  const result = [];

  arr.forEach((fixed, i, arr) => {
    const sliced = arr.slice(i + 1);
    const combi = getCombination(sliced, count - 1);
    const attached = combi.map((c) => [fixed, ...c]);
    result.push(...attached);
  });

  return result;
}

function solution(orders, course) {
  const menuMap = new Map();

  orders.forEach((order) => {
    const orderArr = order.split('').sort();
    course.forEach((n) => {
      getCombination(orderArr, n).forEach((combi) => {
        const menu = combi.join('');
        if (menuMap.has(menu)) {
          menuMap.set(menu, menuMap.get(menu) + 1);
          return;
        }
        menuMap.set(menu, 1);
      });
    });
  });

  const selectedMenuMap = new Map();

  for (const [menu, count] of menuMap.entries()) {
    if (count === 1) {
      continue;
    }

    const key = menu.length;

    if (selectedMenuMap.has(key)) {
      const { maxCount, menus } = selectedMenuMap.get(key);
      if (maxCount < count) {
        selectedMenuMap.set(key, {
          maxCount: count,
          menus: [menu],
        });
      } else if (maxCount === count) {
        menus.push(menu);
      }
      continue;
    }

    selectedMenuMap.set(key, {
      maxCount: count,
      menus: [menu],
    });
  }

  const answer = [];

  for (const { menus } of selectedMenuMap.values()) {
    answer.push(...menus);
  }

  return answer.sort();
}
