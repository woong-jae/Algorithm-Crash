function solution(files) {
  const fileNames = files.map((file) => {
    const name = ['', '', '']; // head, number, tail
    let isAfterNumber = false;

    for (const f of file) {
      if (!name[2] && !isNaN(parseInt(f))) {
        name[1] += f;
        isAfterNumber = true;
        continue;
      }

      if (isAfterNumber) {
        name[2] += f;
        continue;
      }

      name[0] += f;
    }

    return name;
  });

  fileNames.sort((a, b) => {
    const headA = a[0].toLowerCase();
    const headB = b[0].toLowerCase();

    if (headA === headB) {
      return +a[1] - +b[1];
    }

    return headA > headB ? 1 : -1;
  });

  return fileNames.map((fileName) => fileName.join(''));
}