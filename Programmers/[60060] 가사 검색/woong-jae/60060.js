class Node {
    constructor() {
        this.count = 0;
        this.child = new Map();
    }
}

class Trie {
    constructor() {
        this.root = new Node();
    }
    insert(word) {
        let cur = this.root;
        if(!cur.child.has(word.length)) cur.child.set(word.length, new Node());
        cur = cur.child.get(word.length);
        
        for(let char of word) {
            if(!cur.child.has(char)) cur.child.set(char, new Node());
            cur.count++;
            cur = cur.child.get(char);
        }
    }
    getMatch(query) {
        let cur = this.root;
        if(!cur.child.has(query.length)) return 0;
        cur = cur.child.get(query.length);
        
        for(let char of query) {
            if(char === "?") return cur.count;
            if(!cur.child.has(char)) return 0;
            cur = cur.child.get(char);
        }
    }
}

function solution(words, queries) {
    const answer = [];
    
    const reverse = word => {
        return word.split("").reverse().join("");
    }
    
    const trie = new Trie();
    const reverseTrie = new Trie();
    
    words.forEach(word => {
        trie.insert(word);
        reverseTrie.insert(reverse(word));
    });
    
    queries.forEach(query => {
        answer.push(
            query[0] === "?" 
            ? reverseTrie.getMatch(reverse(query)) 
            : trie.getMatch(query)
        )
    });
    
    return answer;
}