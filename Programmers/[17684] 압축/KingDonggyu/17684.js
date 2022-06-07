function solution(msg) {
  const lexicon = new Map();
  const answer = [];

  let i = 0;
  let w = msg[i++];
  let lastIndexNumber = 27;
  
  while (i < msg.length + 1) {
    const c = msg[i++];

    if (lexicon.has(w + c)) {
      w += c;
      continue;
    }

    const indexNumber = w.length === 1 
      ? w.charCodeAt() - 64 
      : lexicon.get(w); 

    answer.push(indexNumber);

    lexicon.set(w + c, lastIndexNumber++);
    w = c;
  }

  return answer;
}