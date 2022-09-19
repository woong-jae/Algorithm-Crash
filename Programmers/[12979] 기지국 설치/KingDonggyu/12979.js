function solution(n, stations, w) {
  const calcStationCount = (count) => {
    return Math.ceil(count / (w * 2 + 1));
  };

  const answer = stations.reduce((acc, cur, i) => {
    let count = cur - w - 1;
    if (i > 0) {
      count -= stations[i - 1] + w;
    }
    return acc + calcStationCount(count);
  }, 0);

  const lastStation = stations[stations.length - 1];

  if (lastStation < n) {
    const count = n - lastStation - w;
    return answer + calcStationCount(count);
  }

  return answer;
}
