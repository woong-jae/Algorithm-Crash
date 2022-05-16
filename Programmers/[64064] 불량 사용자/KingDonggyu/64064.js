function solution(user_id, banned_id) {
  const match = new Array(banned_id.length).fill(0).map((_) => []);

  banned_id.forEach((ban, banIndex) => {
    user_id.forEach((user, userIndex) => {
      if (ban.length !== user.length) {
        return;
      }
      for (let i = 0; i < ban.length; i++) {
        if (ban[i] !== '*' && ban[i] !== user[i]) {
          return;
        }
      }
      match[banIndex].push(userIndex);
    });
  });

  const set = new Set();

  const findBanList = (banIndex, visited) => {
    if (banIndex === banned_id.length) {
      set.add(+visited.join(''));
      return;
    }

    match[banIndex].forEach((userIndex) => {
      if (visited[userIndex]) {
        return;
      }
      visited[userIndex] = 1;
      findBanList(banIndex + 1, visited);
      visited[userIndex] = 0;
    });
  };

  findBanList(0, new Array(user_id.length).fill(0));

  return set.size;
}
