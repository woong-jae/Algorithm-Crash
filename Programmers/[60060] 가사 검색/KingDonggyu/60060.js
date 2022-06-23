function Node() {
  this.count = 0;
  this.child = new Map();
}

function insertTrie (word, root) {
  if (!root.has(word.length)) {
    root.set(word.length, new Node());
  }
  
  let node = root.get(word.length);

  for (const ch of word) {
    if (!node.child.has(ch)) {
      node.child.set(ch, new Node());
    }
    node.count++;
    node = node.child.get(ch);
  }
};

function searchTrie (query, root) {
  if (!root.has(query.length)) {
    return 0;
  }

  let node = root.get(query.length);

  for (const ch of query) {
    if (ch === '?') {
      return node.count;
    }
    if (!node.child.has(ch)) {
      return 0;
    }
    node = node.child.get(ch);
  }
};

function solution(words, queries) {
  String.prototype.reverse = function () {
    return this.split('').reverse().join('');
  };

  const forwardRoot = new Map();
  const backwardRoot = new Map();

  words.forEach((word) => {
    insertTrie(word, forwardRoot);
    insertTrie(word.reverse(), backwardRoot);
  });

  const answer = [];

  queries.forEach((query, i) => {
    answer[i] = (query[0] !== '?')
      ? searchTrie(query, forwardRoot)
      : searchTrie(query.reverse(), backwardRoot);
  });

  return answer;
}
