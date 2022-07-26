class UnionFind {
    constructor() {
        this.root = new Map();
    }
    find(u) {
        if(!this.root.has(u)) this.root.set(u, u);
        if(u === this.root.get(u)) return u;
        this.root.set(u, this.find(this.root.get(u)));
        return this.root.get(u);
    }
    merge(u, v) {
        u = this.find(u);
        v = this.find(v);
        if(u === v) return;
        this.root.set(u, v);
    }
}

function solution(k, room_number) {
    const answer = [];
    const disjointSet = new UnionFind();
    
    room_number.forEach(room => {
        let emptyRoom = disjointSet.find(room);
        answer.push(emptyRoom);
        disjointSet.merge(emptyRoom, emptyRoom + 1);
    });
    
    return answer;
}