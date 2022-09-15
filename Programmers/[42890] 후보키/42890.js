function solution(relation) {
    const colLength = relation[0].length;
    const colIndexes = Array.from(Array(colLength), (_, i) => i);
    
    const allCombination = (elements) => {
        const combination = (elements, pick) => {
            if(pick === 1) return elements.map(elem => [elem]);

            const combinations = [];
            elements.forEach((elem, i) => {
                const smallerCombinations = combination(elements.slice(i + 1), pick - 1);
                smallerCombinations.forEach(smallerCombination => {
                    combinations.push([elem].concat(smallerCombination));
                });
            });

            return combinations;
        }
        
        let result = [];
        for(let i = 1; i <= elements.length; i++) {
            result.push(...combination(elements, i));
        }
        return result;
    }
    
    const isCandidateKey = (elements, picked) => {
        const isUnique = () => {
            const keys = relation.map(row => elements.map(colIndex => row[colIndex]).join());
            const set = new Set(keys);
            
            return set.size === relation.length;
        }
        
        const isMinimal = () => {            
            const elementMask = elements.reduce((acc, elem) => acc | (1 << elem), 0);
            
            return picked.reduce((acc, pick) => {
                if(!acc) return acc;
                
                const pickMask = pick.reduce((acc, elem) => acc | (1 << elem), 0);
                return (pickMask & elementMask) !== pickMask;
            }, true);
        }
        
        return isUnique() && isMinimal();
    }
    
    let answer = [];
    allCombination(colIndexes).forEach(combination => {
        if(isCandidateKey(combination, answer)) answer.push(combination);
    });
    return answer.length;
}
