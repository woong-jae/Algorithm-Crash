function initCourierBox(boxes) {
  while (!!boxes.length && !boxes[boxes.length - 1]) {
    boxes.pop();
  }
}

function handleCourierBox(cap, boxes) {
  while (!!cap && !!boxes.length) {
    const index = boxes.length - 1;

    if (!!boxes[index]) {
      boxes[index] -= 1;
      cap -= 1;
    }

    if (!boxes[index]) {
      boxes.pop();
    }
  }
}

function solution(cap, n, deliveries, pickups) {
  let answer = 0;

  while (!!deliveries.length || !!pickups.length) {
    initCourierBox(deliveries);
    initCourierBox(pickups);

    const maxDistance = Math.max(deliveries.length, pickups.length);

    handleCourierBox(cap, deliveries);
    handleCourierBox(cap, pickups);

    answer += maxDistance * 2;
  }

  return answer;
}
