class Element {
    constructor(cur, time, pressed) {
        this.cur = cur;
        this.time = time;
        this.pressed = pressed;
    }
}

class MinHeap {
    constructor() {
        this.heap = [];
    }
    push(elem) {
        const heap = this.heap;
        heap.push(elem);
        let index = heap.length - 1, parent = Math.floor((index - 1) / 2);
        while(index > 0 && heap[parent].time > heap[index].time) {
            [heap[parent], heap[index]] = [heap[index], heap[parent]];
            index = parent;
            parent = Math.floor((index - 1) / 2);
        }
    }
    pop() {
        const heap = this.heap;
        if(heap.length <= 1) return heap.pop();
        const ret = heap[0];
        heap[0] = heap.pop();
        let here = 0;
        while(1) {
            let left = here * 2 + 1, right = here * 2 + 2;
            if(left >= heap.length - 1) break;
            let next = here;
            if(heap[next].time > heap[left].time) next = left;
            if(right < heap.length - 1 && heap[next].time > heap[right].time) next = right;
            if(next === here) break;
            [heap[next], heap[here]] = [heap[here], heap[next]];
            here = next;
        }
        return ret;
    }
}

function solution(n, start, end, roads, traps) {
    const adjList = Array.from(Array(n + 1), () => []);
    const dist = Array.from(Array(n + 1), () => Array(1024).fill(Infinity));
    const trapsIdx = new Map(traps.map((trap, i) => [trap, i]));
    roads.forEach(([a, b, t]) => {
        adjList[a].push([b, t, 0]); // ORIGINAL
        adjList[b].push([a, t, 1]); // REVERSED
    });
    
    const calcPressed = (pressed, next) => {
        return trapsIdx.has(next) ? pressed ^ (1 << trapsIdx.get(next)) : pressed;
    }
    
    const isValid = (cur, next, pressed, state) => {
        let valid = true;
        if(trapsIdx.has(cur)) {
            if(trapsIdx.has(next)) {
                valid = Boolean(pressed & (1 << trapsIdx.get(cur))) === Boolean(pressed & (1 << trapsIdx.get(next)));
            }
            else {
                valid = !(pressed & (1 << trapsIdx.get(cur)));
            }
        }
        else {
            if(trapsIdx.has(next)) {
                valid = !(pressed & (1 << trapsIdx.get(next)));
            }
        }
        return state === 0 ? valid : !valid;
    }
    
    const pq = new MinHeap();
    pq.push(new Element(start, 0, 0));
    dist[start][0] = 0;
    while(pq.heap.length) {
        const { cur, time, pressed } = pq.pop();
        if(dist[cur][pressed] < time) continue;
        for(let [next, nextTime, state] of adjList[cur]) {
            if(!isValid(cur, next, pressed, state)) continue;
            const nextPressed = calcPressed(pressed, next);
            if(dist[next][nextPressed] > time + nextTime) {
                dist[next][nextPressed] = time + nextTime;
                pq.push(new Element(next, time + nextTime, nextPressed));
            }
        }
    }
    
    return Math.min(...dist[end]);
}
