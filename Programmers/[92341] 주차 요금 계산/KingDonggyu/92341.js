function solution(fees, records) {
  const convertMinute = (time) => {
    const [hour, minute] = time.split(':');
    return +hour * 60 + +minute;
  };

  const calculateFee = (time) => {
    if (fees[0] >= time) {
      return fees[1];
    }
    return fees[1] + Math.ceil((time - fees[0]) / fees[2]) * fees[3];
  };

  const parkingInfo = new Map();

  records.forEach((record) => {
    let [time, num, act] = record.split(' ');

    time = convertMinute(time);

    if (parkingInfo.has(num)) {
      const info = parkingInfo.get(num);
      if (act === 'IN') {
        info.entryTime = time;
        return;
      }
      info.totalTime += time - info.entryTime;
      info.entryTime = null;
      return;
    }

    parkingInfo.set(num, {
      entryTime: time,
      totalTime: 0,
    });
  });

  const answer = [];
  const sortedNums = Array.from(parkingInfo.keys()).sort();

  sortedNums.forEach((num) => {
    let { entryTime, totalTime } = parkingInfo.get(num);

    if (entryTime !== null) {
      totalTime += convertMinute('23:59') - entryTime;
    }

    answer.push(calculateFee(totalTime));
  });

  return answer;
}
