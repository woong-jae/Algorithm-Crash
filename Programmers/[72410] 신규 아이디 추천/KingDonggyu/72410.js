function solution(new_id) {
  let answer = new_id
    .toLowerCase()
    .replace(/[^\w\-\.]/g, '')
    .replace(/\.+/g, '.')
    .replace(/^\.|\.$/g, '')
    .replace(/^$/, 'a')
    .slice(0, 15)
    .replace(/\.$/, '');

  while (answer.length <= 2) {
    answer += answer[answer.length - 1];
  }

  return answer;
}
