function solution(lines) {
  const timeline = [];
  let answer = 1;

  lines.forEach((line) => {
    const startTime = new Date(line.slice(0, 23));
    const endTime = new Date(startTime.getTime());

    const T = +line.slice(24, line.length - 1);
    const seconds = Math.floor(T);
    const milliseconds = (T % 1) * 1000;

    startTime.setSeconds(startTime.getSeconds() - seconds - 1);
    startTime.setMilliseconds(startTime.getMilliseconds() - milliseconds + 2);

    for (let i = timeline.length - 1; i >= 0; i--) {
      if (startTime > timeline[i].end) break;
      answer = Math.max(answer, ++timeline[i].count);
    }

    timeline.push({ end: endTime, count: 1 });
  });

  return answer;
}