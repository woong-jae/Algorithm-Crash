function solution(n, k) {
    n = n.toString(k);
    
    const isPrime = num => {
        if(num < 2) return false;
        for(let i = 2; i * i <= num; i++) {
            if(num % i === 0) return false;
        }
        return true;
    }
    
    return n.split("0").reduce((acc, cur) => acc + (isPrime(+cur) ? 1 : 0), 0);
}