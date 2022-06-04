function solution(food_times, k) {
  const foodCount = food_times.length;
  const foods = food_times
    .map((time, i) => ({ number: i + 1, time: time }))
    .sort((a, b) => a.time - b.time);

  let prevTime = 0;

  for (let i = 0; i < foodCount; i++) {
    const leftoverCount = foodCount - i;
    const eatTime = (foods[i].time - prevTime) * leftoverCount;

    prevTime = foods[i].time;

    if (k < eatTime) {
      const leftoverFoods = foods.slice(i).sort((a, b) => a.number - b.number);
      return leftoverFoods[k % leftoverCount].number;
    }

    k -= eatTime;
  }

  return -1;
}
