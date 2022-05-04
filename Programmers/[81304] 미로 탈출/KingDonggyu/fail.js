function solution(n, start, end, roads, traps) {
  const edges = new Array(n + 1).fill(null).map((_) => []);
  const bidirection = new Map();

  for (let [v1, v2, w] of roads) {
    edges[v1].push([v2, w]);
    edges[v2].push([v1, -w]);

    if (v2 < v1) {
      [v1, v2] = [v2, v1];
    }

    const key = `${v1}${v2}`;
    if (bidirection.has(key)) {
      bidirection.set(key, 2);
    } else {
      bidirection.set(key, 1);
    }
  }

  // null: 함정 x, false: 함정 off, true: 힘정 on
  const trapInfo = new Array(n + 1).fill(null);

  for (const trap of traps) {
    trapInfo[trap] = false;
  }

  const visited = new Array(n + 1).fill(false);
  const time = new Array(n + 1).fill(Infinity);
  time[start] = 0;

  const getMinTimeRoom = () => {
    let room = 0;
    for (let i = 1; i < n + 1; i++) {
      if (!visited[i] && time[room] > time[i]) {
        room = i;
      }
    }
    return room;
  };

  let now = start;

  while (now !== end) {
    visited[now] = true;

    edges[now].forEach(([next, w]) => {
      const isBackward = (trapInfo[now] + trapInfo[next]) % 2;

      if (!isBackward && w > 0) {
        time[next] = Math.min(time[next], time[now] + w);
      } else if (isBackward && w < 0) {
        time[next] = Math.min(time[next], time[now] + -w);
      }
    });

    const next = getMinTimeRoom();

    if (trapInfo[next] !== null) {
      trapInfo[next] = !trapInfo[next];
    }

    let key;

    if (now < next) {
      key = `${now}${next}`;
    } else {
      key = `${next}${now}`;
    }

    if (bidirection.get(key) === 2 && (trapInfo[now] || trapInfo[next])) {
      visited[now] = false;
      time[now] = Infinity;
    } else if (trapInfo[now] && trapInfo[next]) {
      visited[now] = false;
      time[now] = Infinity;
    }

    now = next;
  }

  return time[end];
}