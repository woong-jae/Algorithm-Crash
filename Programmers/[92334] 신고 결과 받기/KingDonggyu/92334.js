function solution(id_list, report, k) {
  const reportedMap = new Map();

  report.forEach((str) => {
    const [a, b] = str.split(' ');
    if (reportedMap.has(b)) {
      reportedMap.get(b).add(a);
      return;
    }
    reportedMap.set(b, new Set([a]));
  });

  const userMap = new Map(id_list.map((id) => [id, 0]));

  for (const users of reportedMap.values()) {
    if (users.size < k) {
      continue;
    }
    users.forEach((user) => {
      userMap.set(user, userMap.get(user) + 1);
    });
  }

  return id_list.map((id) => userMap.get(id));
}
