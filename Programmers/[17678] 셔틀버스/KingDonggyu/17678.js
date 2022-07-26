function solution(n, t, m, timetable) {
  let answer = '';
  let time = '09:00';

  timetable.sort((a, b) => (a < b ? 1 : -1));

  const getNextTime = (target, isEnd) => {
    const date = new Date();
    date.setHours(target[0] + target[1]);

    if (isEnd) {
      date.setMinutes(+(target[3] + target[4]) - 1);
    } else {
      date.setMinutes(+(target[3] + target[4]) + t);
    }

    let hour = date.getHours();
    let minute = date.getMinutes();

    if (hour < 10) hour = `0${hour}`;
    if (minute < 10) minute = `0${minute}`;

    return `${hour}:${minute}`;
  }

  while (n--) {
    let count = m;
    while (count-- && timetable.length) {
      if (time < timetable[timetable.length - 1]) {
        break;
      }
      answer = timetable.pop();
    }

    if (!n) {
      if (count >= 0) {
        answer = time;
      } else {
        answer = getNextTime(answer, true);
      }
      break;
    }

    time = getNextTime(time, false);
  }

  return answer;
}