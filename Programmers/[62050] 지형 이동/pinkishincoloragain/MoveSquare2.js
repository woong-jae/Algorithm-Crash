class PriorityQ {
  constructor() {
    this.arr = new Array();
    this.arr.push("");
  }

  push(cost, elem) {
    this.arr.push([cost, elem]);
    let curPosition = this.arr.length - 1;

    while (
      1 < curPosition &&
      this.arr[curPosition][0] < this.arr[Math.floor(curPosition / 2)][0]
    ) {
      let tmp = this.arr[Math.floor(curPosition / 2)];
      this.arr[Math.floor(curPosition / 2)] = this.arr[curPosition];
      this.arr[curPosition] = tmp;
      curPosition = Math.floor(curPosition / 2);
    }
  }

  pop() {
    if (this.arr.length <= 1) return null;
    let curPosition = 1;
    let last = this.arr[curPosition];
    if (2 < this.arr.length) this.arr[curPosition] = this.arr.pop();
    else this.arr.pop();

    while (curPosition * 2 < this.arr.length) {
      if (
        curPosition * 2 + 1 < this.arr.length &&
        this.arr[curPosition * 2 + 1][0] <= this.arr[curPosition * 2][0] &&
        this.arr[curPosition * 2 + 1][0] < this.arr[curPosition][0]
      ) {
        //양쪽이 있고 왼쪽이 작을 경우
        let tmp = this.arr[curPosition * 2 + 1];
        this.arr[curPosition * 2 + 1] = this.arr[curPosition];
        this.arr[curPosition] = tmp;
        curPosition = curPosition * 2 + 1;
      } else if (this.arr[curPosition * 2][0] < this.arr[curPosition][0]) {
        let tmp = this.arr[curPosition * 2];
        this.arr[curPosition * 2] = this.arr[curPosition];
        this.arr[curPosition] = tmp;
        curPosition = curPosition * 2;
      } else {
        break;
      }
    }

    return last;
  }
}

function solution(land, height) {
  let visited = new Array(land.length)
    .fill(0)
    .map(_ => new Array(land[0].length).fill(0));

  return searchMap(0, 0, visited, land, height);
}

function searchMap(row, col, visited, land, height) {
  const up = [-1, 0, 1, 0];
  const right = [0, -1, 0, 1];

  let queue = new Array([0, row, col]); //cost, row, col
  let result = 0;
  let priorityQ = new PriorityQ();

  while (queue.length) {
    let [cost, rr, cc] = queue.shift();
    if (visited[rr][cc] == 0) {
      visited[rr][cc] = 1;
      result += cost;

      for (let i = 0; i < 4; i++) {
        let tRr = rr + up[i];
        let tCc = cc + right[i];

        if (
          tRr < 0 ||
          tCc < 0 ||
          land.length <= tRr ||
          land[0].length <= tCc ||
          0 < visited[tRr][tCc]
        )
          continue;

        let diff = Math.abs(land[tRr][tCc] - land[rr][cc]);

        if (diff <= height) queue.push([0, tRr, tCc]);
        else priorityQ.push(diff, [tRr, tCc]);
      }
    }

    if (queue.length == 0) {
      let next = priorityQ.pop();
      if (next != null) queue.push([next[0], ...next[1]]);
    }
  }

  return result;
}
console.log(
  solution(
    [
      [1, 4, 8, 10],
      [5, 5, 5, 5],
      [10, 10, 10, 10],
      [10, 10, 10, 20],
    ],
    3
  )
);
console.log(
  solution(
    [
      [10, 11, 10, 11],
      [2, 21, 20, 10],
      [1, 20, 21, 11],
      [2, 1, 2, 1],
    ],
    1
  )
);
