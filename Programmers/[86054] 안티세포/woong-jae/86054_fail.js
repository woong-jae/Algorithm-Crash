function solution(a, s) {
    const result = [];

    const find = (u, root) => {
        if (u === root[u]) return u;
        return root[u] = find(root[u], root);
    }

    const merge = (u, v, root) => {
        u = find(u, root);
        v = find(v, root);

        if (u === v) return;

        root[v] = u;
    }

    const countPossibleStateFrom = b => {
        let states = 0;
        const acc = [...b];
        for (let i = 1; i < acc.length; i++) acc[i] += acc[i - 1];

        const sum = (start, end) => {
            if (start === 0) return acc[end];
            return acc[end] - acc[start - 1];
        }

        const simulate = (i, root) => {
            if (i >= b.length) {
                states++;
                return;
            }

            const x = find(i, root);
            if (x > 0) {
                const y = find(x - 1, root);

                if (sum(x, i) === sum(y, x - 1)) {
                    const next_root = [...root];
                    merge(y, x, next_root);

                    simulate(i, next_root);
                }
            }

            simulate(i + 1, [...root]);
        }

        simulate(0, Array.from(Array(b.length), (_, i) => i));

        return states;
    }

    s.reduce((startIndex, length) => {
        const b = a.slice(startIndex, startIndex + length);
        result.push(countPossibleStateFrom(b));

        return startIndex + length;
    }, 0);

    return result;
}

console.log(solution([1, 1, 1, 1, 1, 1, 2, 5, 8, 2, 1, 1, 4, 8, 8, 8, 12, 6, 6], [4, 3, 1, 5, 6]))