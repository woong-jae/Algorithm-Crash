function solution(play_time, adv_time, logs) {
  play_time = getSeconds(play_time);
  adv_time = getSeconds(adv_time);

  const playCount = Array(play_time + 1).fill(0);

  logs.map((log) => {
    const [start, end] = log.split('-');
    playCount[getSeconds(start)]++;
    playCount[getSeconds(end)]--;
  });

  // 시청자 수 구하기
  for (let i = 1; i <= play_time; i++) {
    playCount[i] += playCount[i - 1];
  }

  // 누적 시청자 수 구하기
  for (let i = 1; i <= play_time; i++) {
    playCount[i] += playCount[i - 1];
  }

  let answer = 0;
  let cumulativeTime = playCount[adv_time - 1];

  // 누적 재생시간 최대값 구하기
  for (let i = adv_time; i <= play_time; i++) {
    if (cumulativeTime < playCount[i] - playCount[i - adv_time]) {
      cumulativeTime = playCount[i] - playCount[i - adv_time];
      answer = i - adv_time + 1;
    }
  }

  return formatTime(answer).join(':');
}

const getSeconds = (time) => {
  const [h, m, s] = time.split(':');
  return h * 3600 + m * 60 + s * 1;
};

const formatTime = (seconds) => {
  let h = Math.floor(seconds / 3600);
  let m = Math.floor((seconds - hour * 3600) / 60);
  let s = seconds - hour * 3600 - minute * 60;

  return [h, m, s].map((time) => (time > 9 ? time : '0' + time));
};
