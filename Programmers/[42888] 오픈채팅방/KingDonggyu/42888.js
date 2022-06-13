function solution(record) {
  const userMap = new Map();
  const inoutRecord = [];

  record.forEach((str) => {
    const splited = str.split(' ');
    if (splited[0] === 'Change' || splited[0] === 'Enter') {
      userMap.set(splited[1], splited[2]);
    }

    if (splited[0] !== 'Change') {
      inoutRecord.push({
        id: splited[1],
        action: splited[0],
      });
    }
  });

  const answer = [];

  inoutRecord.forEach(({ id, action }) => {
    const nickName = userMap.get(id);
    if (action === 'Enter') {
      answer.push(`${nickName}님이 들어왔습니다.`);
      return;
    }
    answer.push(`${nickName}님이 나갔습니다.`);
  });

  return answer;
}
