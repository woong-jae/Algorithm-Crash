class Node {
  constructor(value) {
    this.value = value;
    this.next = null;
    this.prev = null;
  }
}

function solution(n, k, cmd) {
  const answer = [];
  const removed = [];

  let selected = null;
  let head = null;

  // Linked List 생성
  for (let row = 0; row < n; row++) {
    answer[row] = 'O';
    const node = new Node(row);

    if (!head) {
      head = node;
      continue;
    }

    head.next = node;
    node.prev = head;
    head = head.next;

    if (row === k) {
      selected = node;
    }
  }

  for (const s of cmd) {
    let [ch, count] = s.split(' ');
    count = Number(count);

    switch (ch) {
      case 'C':
        removed.push(selected);
        answer[selected.value] = 'X';

        if (selected.prev) {
          selected.prev.next = selected.next;
        }

        if (selected.next) {
          selected.next.prev = selected.prev;
          // 삭제 후 아래 행 선택
          selected = selected.next;
        } else {
          // 삭제 후 윗 행 선택
          selected = selected.prev;
        }
        break;
      case 'Z': 
        const restore = removed.pop();
        answer[restore.value] = 'O';

        if (restore.prev) {
          restore.prev.next = restore;
        }

        if (restore.next) {
          restore.next.prev = restore;
        }
        break;
      case 'D': 
        while (count--) {
          selected = selected.next;
        }
        break;
      case 'U': 
        while (count--) {
          selected = selected.prev;
        }
    }
  }

  return answer.join('');
}
