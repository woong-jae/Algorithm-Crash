function solution(n, s, a, b, fares) {
  const routes = new Array(n + 1).fill(0)
    .map((_) => new Array(n + 1).fill(0));

  fares.forEach(([c, d, f]) => {
    routes[c][d] = f;
    routes[d][c] = f;
  });

  const minFares_S = new Array(n + 1).fill(Infinity);
  const minFares_A = new Array(n + 1).fill(Infinity);
  const minFares_B = new Array(n + 1).fill(Infinity);

  const setMinFare = (minFares, start) => {
    const stack = [start];
    minFares[start] = 0;

    while (stack.length) {
      const spot = stack.pop();
      routes[spot].forEach((fare, nextSpot) => {
        if (!fare || minFares[nextSpot] <= minFares[spot] + fare) {
          return;
        }
        minFares[nextSpot] = minFares[spot] + fare;
        stack.push(nextSpot);
      });
    }
  };

  setMinFare(minFares_S, s);
  setMinFare(minFares_A, a);
  setMinFare(minFares_B, b);

  let answer = Infinity;

  for (let i = 1; i <= n; i++) {
    const totalFare = minFares_S[i] + minFares_A[i] + minFares_B[i];
    answer = Math.min(answer, totalFare);
  }

  return answer;
}
