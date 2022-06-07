function replaceSharp(melody, music) {
  const targets = [
    [/C#/g, 'c'],
    [/D#/g, 'd'],
    [/F#/g, 'f'],
    [/G#/g, 'g'],
    [/A#/g, 'a'],
  ];

  targets.forEach((target) => {
    melody = melody.replace(target[0], target[1]);
    music = music.replace(target[0], target[1]);
  });

  return [melody, music];
}

function getPlayTime(start, end) {
  const minutes = [start, end].map((time) => {
    const splited = time.split(':');
    return +splited[0] * 60 + +splited[1];
  });

  return minutes[1] - minutes[0];
}

function checkSameMusic(melody, music, playTime) {
  if (playTime < music.length) {
    music = music.slice(0, playTime);
  } else {
    while (playTime > music.length) {
      music += music;
    }
  }

  return music.includes(melody);
}

function solution(m, musicinfos) {
  const answer = { title: null, playTime: 0 };

  musicinfos.forEach((musicinfo) => {
    const [start, end, title, music] = musicinfo.split(',');
    const replaced = replaceSharp(m, music);
    const playTime = getPlayTime(start, end);

    if (!checkSameMusic(...replaced, playTime)) {
      return;
    }

    if (answer.playTime < playTime) {
      answer.title = title;
      answer.playTime = playTime;
    }
  });

  if (!answer.title) {
    return '(None)';
  }

  return answer.title;
}
