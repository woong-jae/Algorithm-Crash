function solution(food_times, k) {
    const sorted_food_times = food_times.slice(0).sort((a, b) => a - b);
    
    let ate = 0, i = 0;
    for(i; i < food_times.length; i++) {
        const leftFood = food_times.length - i;
        const neededTime = (sorted_food_times[i] - ate) * leftFood;
        if(neededTime > k) break;
        ate += (sorted_food_times[i] - ate);
        k -= neededTime;
    }
    if(i === food_times.length) return -1;

    const reduced_food_times = [];
    food_times.forEach((time, index) => {
        if(time <= ate) return;
        reduced_food_times.push([time - ate, index + 1]);
    });
    
    return reduced_food_times[k % reduced_food_times.length][1];
}
