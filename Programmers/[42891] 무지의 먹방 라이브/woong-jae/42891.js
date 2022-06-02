class Node {
    constructor(index) {
        this.index = index;
        this.next = null
    }
}

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

    let head = null, tail = null, reducedLength = 0;
    food_times.map(time => time >= ate ? time - ate : 0).forEach((time, index) => {
        if(time === 0) return;
        reducedLength++;
        if(!head) {
            head = new Node(index + 1);
            tail = head;
        }
        else {
            tail.next = new Node(index + 1);
            tail = tail.next;
        }
    });
    tail.next = head;
    
    k = k % reducedLength;
    let cur = head, prev = tail;
    while(k--) cur = cur.next;
    
    return cur.index;
}