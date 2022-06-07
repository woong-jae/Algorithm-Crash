function setMatchingScore(pageinfo) {
  for (const url of pageinfo.keys()) {
    const { links, baseScore } = pageinfo.get(url);

    links.forEach((link) => {
      if (!pageinfo.has(link)) {
        return;
      }

      const targetPage = pageinfo.get(link);
      const linkScore = baseScore / links.length;

      targetPage.matchingScore += linkScore;
    });
  }
}

function getBaseScore(page, word) {
  word = word.toLowerCase();

  const searchWords = page.split(/[^a-z]/i).filter((pageWord) => {
    return pageWord.toLowerCase() === word;
  });

  return searchWords.length;
}

function getLinks(page) {
  const aTags = page.match(/<a href="\S*"/g);

  if (aTags === null) {
    return [];
  }

  const links = aTags.map(tag => {
    return tag.split('https://')[1].slice(0, -1);
  });

  return links;
}

function getUrl(page) {
  const metaTag = page.match(/<meta property="og:url" content="\S*/)[0];
  const url = metaTag.split('https://')[1].slice(0, -3);

  return url;
}

function solution(word, pages) {
  const pageinfo = new Map();

  pages.map((page, index) => {
    const url = getUrl(page);
    const links = getLinks(page);
    const baseScore = getBaseScore(page, word);

    pageinfo.set(url, {
      index, url, links, baseScore,
      matchingScore: baseScore
    });
  });

  setMatchingScore(pageinfo);

  let maxScore = 0;
  let answer = 0;

  for (const url of pageinfo.keys()) {
    const { index, matchingScore } = pageinfo.get(url);
    if (maxScore < matchingScore) {
      [maxScore, answer] = [matchingScore, index];
    }
  }

  return answer;
}
