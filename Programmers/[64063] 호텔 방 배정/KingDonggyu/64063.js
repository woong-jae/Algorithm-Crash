function solution(k, room_number) {
  const map = new Map();
  const answer = [];

  const getRoomNumber = (x) => {
    if (map.has(x)) {
      const next = getRoomNumber(map.get(x));
      map.set(x, next + 1);
      return next;
    }
    map.set(x, x + 1);
    return x;
  };

  room_number.forEach((num) => {
    const emptyRoom = getRoomNumber(num);
    answer.push(emptyRoom);
  });

  return answer;
}
